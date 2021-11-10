package com.anafthdev.imdbmovie.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.anafthdev.imdbmovie.ui.theme.default_primary
import com.anafthdev.imdbmovie.ui.theme.text_color

@OptIn(
	ExperimentalMaterialApi::class,
	ExperimentalUnitApi::class,
	ExperimentalComposeUiApi::class
)
@Composable
fun SetAPIKeyDialog(
	onSave: (String) -> Unit,
	onDismiss: () -> Unit
) {
	Dialog(
		onDismissRequest = {
			onDismiss()
		},
		properties = DialogProperties(usePlatformDefaultWidth = false),
		content = {
			SetAPIKeyDialogItem {
				onSave(it)
			}
		}
	)
}

@OptIn(
	ExperimentalMaterialApi::class,
	ExperimentalUnitApi::class
)
@Preview
@Composable
fun SetAPIKeyDialogItem(
	onSave: (String) -> Unit = {}
) {
	val uriHandler = LocalUriHandler.current
	var apiKeyInput by remember { mutableStateOf("") }
	var isTextFieldError by remember { mutableStateOf(false) }
	
	Card(
		shape = RoundedCornerShape(16.dp),
		modifier = Modifier
			.padding(start = 16.dp, end = 16.dp)
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(8.dp)
		) {
			OutlinedTextField(
				value = apiKeyInput,
				isError = isTextFieldError,
				label = { Text("API Key") },
				onValueChange = {
					apiKeyInput = it
					isTextFieldError = it.isBlank()
				},
				trailingIcon = {
					if (isTextFieldError) {
						Icon(
							imageVector = Icons.Filled.Info,
							tint = MaterialTheme.colors.error,
							contentDescription = null
						)
					}
				},
				modifier = Modifier
					.fillMaxWidth()
			)
			
			if (isTextFieldError) {
				Text(
					text = "API key cannot be empty!",
					color = MaterialTheme.colors.error,
					style = MaterialTheme.typography.caption
				)
			}
			
			val annotatedString = buildAnnotatedString {
				withStyle(SpanStyle(
					color = text_color,
					fontSize = TextUnit(14f, TextUnitType.Sp)
				)) {
					append("Set API key (")
				}
				
				withStyle(SpanStyle(
					color = default_primary,
					fontSize = TextUnit(14f, TextUnitType.Sp),
					textDecoration = TextDecoration.Underline
				)) {
					append("https://imdb-api.com/")
				}
				
				addStringAnnotation(
					tag = "imdb_api_link",
					annotation = "https://imdb-api.com/",
					start = 12,
					end = 33
				)
				
				withStyle(SpanStyle(
					color = text_color,
					fontSize = TextUnit(14f, TextUnitType.Sp)
				)) {
					append(") or use sample data")
				}
			}
			
			ClickableText(
				text = annotatedString,
				onClick = {
					annotatedString
						.getStringAnnotations(
							"imdb_api_link",
							it,
							it
						)
						.firstOrNull()?.let { stringAnnotation ->
							uriHandler.openUri(stringAnnotation.item)
						}
				},
				modifier = Modifier
					.padding(top = 8.dp)
			)
			
			Button(
				onClick = {
					if (apiKeyInput.isBlank()) {
						isTextFieldError = true
					} else onSave(apiKeyInput)
				},
				modifier = Modifier
					.align(Alignment.End)
					.padding(end = 0.dp, top = 4.dp)
			) {
				Text("Save")
			}
		}
	}
}
