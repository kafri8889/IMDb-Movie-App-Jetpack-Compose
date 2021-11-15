package com.anafthdev.notepadcompose.utils

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
	
	object Shimmer {
		
		const val START = "start_shimmer"
		const val STOP = "stop_shimmer"
		
		data class Shimmer(val tag: String, var state: String, val  animationDurationInMillis: Int = 1000)
		
		@Composable
		fun createShimmer(vararg tag: String): List<Shimmer> {
			if (tag.isEmpty()) return emptyList()
			
			val shimmerList = ArrayList<Shimmer>()
			tag.forEach {
				shimmerList.add(
					Shimmer(
						it,
						remember { mutableStateOf(START) }.value
					)
				)
			}
			
			return shimmerList
		}
		
		@SuppressLint("UnnecessaryComposedModifier")
		fun Modifier.applyShimmer(
			state: String = START,
			animationDurationInMillis: Int = 1000
		) = composed { then(
			run {
				val transition = rememberInfiniteTransition()
				val translateAnim by transition.animateFloat(
					initialValue = 0f,
					targetValue = 2000f,
					animationSpec = infiniteRepeatable(
						tween(durationMillis = animationDurationInMillis, easing = FastOutSlowInEasing),
						RepeatMode.Reverse
					)
				)
				
				val brush = Brush.linearGradient(
					colors = listOf(
						Color.LightGray.copy(0.9f),
						Color.LightGray.copy(0.2f),
						Color.LightGray.copy(0.9f)
					),
					start = Offset(10f, 10f),
					end = Offset(translateAnim, translateAnim)
				)
				
				if (state == START) background(brush) else background(color = Color.Transparent)
			}
		) }
		
		@SuppressLint("UnnecessaryComposedModifier")
		fun Modifier.applyShimmer(
			shimmer: Shimmer,
			animationDurationInMillis: Int = 1000
		) = composed { then(
			run {
				val transition = rememberInfiniteTransition()
				val translateAnim by transition.animateFloat(
					initialValue = 0f,
					targetValue = 2000f,
					animationSpec = infiniteRepeatable(
						tween(durationMillis = animationDurationInMillis, easing = FastOutSlowInEasing),
						RepeatMode.Reverse
					)
				)
				
				val brush = Brush.linearGradient(
					colors = listOf(
						Color.LightGray.copy(0.9f),
						Color.LightGray.copy(0.2f),
						Color.LightGray.copy(0.9f)
					),
					start = Offset(10f, 10f),
					end = Offset(translateAnim, translateAnim)
				)
				
				if (shimmer.state == START) background(brush) else background(color = Color.Transparent)
			}
		) }
		
	}
	
	object BounceEffect {
		
		data class Bounce(val tag: String, var bounceState: BounceState)
	
		enum class BounceState { Pressed, Release }
		
		@Composable
		fun createBounce(vararg tag: String): List<Bounce> {
			
			val bounceList = ArrayList<Bounce>()
			tag.forEach {
				bounceList.add(
					Bounce(
						it,
						BounceState.Release
					)
				)
			}
			
			return bounceList
		}
		
		fun Modifier.applyBounce(
			bounce: Bounce,
			sizeReduction: Float = 0.95f
		) = composed { then(
			run {
				val transition = updateTransition(
					targetState = bounce.bounceState,
					label = "animation"
				)
				
				val scale by transition.animateFloat(
					transitionSpec = {
						spring(stiffness = 900f)
					},
					label = ""
				) { bounceState ->
					if (bounceState == BounceState.Pressed) {
						sizeReduction
					} else 1f
				}
				
				graphicsLayer(
					scaleX = scale,
					scaleY = scale
				)
			}
		) }
		
		fun Modifier.applyBounce(
			bounceState: BounceState,
			sizeReduction: Float = 0.95f
		) = composed { then(
			run {
				val transition = updateTransition(
					targetState = bounceState,
					label = "animation"
				)
				
				val scale by transition.animateFloat(
					transitionSpec = {
						spring(stiffness = 900f)
					},
					label = ""
				) { bounceState ->
					if (bounceState == BounceState.Pressed) {
						sizeReduction
					} else 1f
				}
				
				graphicsLayer(
					scaleX = scale,
					scaleY = scale
				)
			}
		) }
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