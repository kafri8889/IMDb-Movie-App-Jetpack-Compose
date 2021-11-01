package com.anafthdev.notepadcompose.utils

import androidx.activity.ComponentActivity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

object ComposeUtils {
	
	open class OverflowMenu {
		
		data class Menu(val name: String, @DrawableRes val icon: Int? = null)
		
		sealed class OverflowMenuIconSize(val size: Dp)
		
		object ICON_BUTTON_SIZE_48_DP: OverflowMenuIconSize(48.dp)
		object ICON_BUTTON_SIZE_44_DP: OverflowMenuIconSize(44.dp)
		object ICON_BUTTON_SIZE_40_DP: OverflowMenuIconSize(40.dp)
		object ICON_BUTTON_SIZE_36_DP: OverflowMenuIconSize(36.dp)
		object ICON_BUTTON_SIZE_32_DP: OverflowMenuIconSize(32.dp)
		object ICON_BUTTON_SIZE_28_DP: OverflowMenuIconSize(28.dp)
		object ICON_BUTTON_SIZE_24_DP: OverflowMenuIconSize(24.dp)
		
		data class MenuAction(
			val menu: Menu,
			val onClick: () -> Unit
		)
		
		companion object {
			@Composable
			fun OverflowMenu(
				menuActions: List<MenuAction>,
				menuIconSize: OverflowMenuIconSize = ICON_BUTTON_SIZE_40_DP,
				maxShowedIcon: Int = 3
			) {
				var showPopupMenu by remember { mutableStateOf(false) }
				
				val menuWithoutIcon = ArrayList(menuActions.filter { it.menu.icon == null })
				val menuWithIcon = menuActions.filter { it.menu.icon != null }
				
				if (menuWithIcon.isEmpty() and menuWithoutIcon.isEmpty()) return
				else if (menuWithoutIcon.isEmpty() and menuWithIcon.isNotEmpty()) {
					Row {
						menuWithIcon.forEachIndexed { index, menuAction ->
							if (index < maxShowedIcon) {
								IconButton(
									modifier = Modifier.size(menuIconSize.size),
									onClick = menuAction.onClick
								) {
									Icon(
										painter = painterResource(id = menuAction.menu.icon!!),
										contentDescription = menuAction.menu.name
									)
								}
							}
						}
					}
				} else if (menuWithoutIcon.isNotEmpty() and menuWithIcon.isEmpty()) {
					IconButton(onClick = { showPopupMenu = !showPopupMenu }) {
						Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null)
					}
					
					DropdownMenu(
						expanded = showPopupMenu,
						onDismissRequest = { showPopupMenu = false }
					) {
						menuWithoutIcon.forEach {
							DropdownMenuItem(onClick = { it.onClick.invoke().also { showPopupMenu = false } }) {
								Text(text = it.menu.name)
							}
						}
					}
				} else {
					Row {
						menuWithIcon.forEachIndexed { index, menuAction ->
							if (index < maxShowedIcon - 1) {
								IconButton(
									modifier = Modifier.size(menuIconSize.size),
									onClick = menuAction.onClick
								) {
									Icon(
										painter = painterResource(id = menuAction.menu.icon!!),
										contentDescription = menuAction.menu.name
									)
								}
							} else menuWithoutIcon.add(menuAction)  // Move the menu to the menu section withoutIcon
						}
					}
					
					IconButton(
						onClick = { showPopupMenu = !showPopupMenu }
					) {
						Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null)
					}
					
					DropdownMenu(
						expanded = showPopupMenu,
						onDismissRequest = { showPopupMenu = false }
					) {
						menuWithoutIcon.forEach {
							DropdownMenuItem(onClick = { it.onClick.invoke().also { showPopupMenu = false } }) {
								Text(text = it.menu.name)
							}
						}
					}
				}
			}
		}
	}
	
	@Composable
	fun ComponentActivity.LifecycleEventListener(event: (Lifecycle.Event) -> Unit) {
		val eventHandler by rememberUpdatedState(newValue = event)
		val lifecycle = this@LifecycleEventListener.lifecycle
		DisposableEffect(lifecycle) {
			val observer = LifecycleEventObserver { _, event ->
				eventHandler(event)
			}
			
			lifecycle.addObserver(observer)
			
			onDispose {
				lifecycle.removeObserver(observer)
			}
		}
	}
}