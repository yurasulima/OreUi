package io.dangerous.oreui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.dangerous.oreui.component.OreButton
import io.dangerous.oreui.component.OreButtonVariant
import io.dangerous.oreui.component.OreCard
import io.dangerous.oreui.component.OreCheckbox
import io.dangerous.oreui.component.OreCloseButton
import io.dangerous.oreui.component.OreDivider
import io.dangerous.oreui.component.OreDropdown
import io.dangerous.oreui.component.OreDropdownOption
import io.dangerous.oreui.component.OreMenuItem
import io.dangerous.oreui.component.OreSlider
import io.dangerous.oreui.component.OreSwitch
import io.dangerous.oreui.component.OreTextField
import io.dangerous.oreui.layout.OreScreen

@Composable
fun OreUiSample() {
    val colors = LocalOreColors.current
    var text by remember { mutableStateOf("") }
    var sliderValue by remember { mutableStateOf(0.5f) }
    var steppedSliderValue by remember { mutableStateOf(25f) }
    var switchChecked by remember { mutableStateOf(true) }
    var checkboxChecked by remember { mutableStateOf(true) }
    var selectedDropdownValue by remember { mutableStateOf("en") }

    val dropdownOptions = remember {
        listOf(
            OreDropdownOption("Англійська", "en"),
            OreDropdownOption("Українська", "uk"),
            OreDropdownOption("Німецька", "de"),
            OreDropdownOption("Іспанська", "es")
        )
    }

    OreScreen(
        title = "Демо Blink UI",
        onBack = {},
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Section("Профіль користувача та керування закриттям") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .border(3.dp, colors.outline, RectangleShape)
                                .background(colors.surfaceSunken)
                                .padding(3.dp)
                                .drawBehind {
                                    val px = size.width / 8f
                                    drawRect(Color(0xFFC68E62))
                                    drawRect(Color(0xFF4A3121), topLeft = Offset(0f, 0f), size = Size(size.width, 2.5f * px))
                                    drawRect(Color(0xFFFFFFFF), topLeft = Offset(1f * px, 4f * px), size = Size(1f * px, 1f * px))
                                    drawRect(Color(0xFF4C4C99), topLeft = Offset(2f * px, 4f * px), size = Size(1f * px, 1f * px))
                                    drawRect(Color(0xFFFFFFFF), topLeft = Offset(6f * px, 4f * px), size = Size(1f * px, 1f * px))
                                    drawRect(Color(0xFF4C4C99), topLeft = Offset(5f * px, 4f * px), size = Size(1f * px, 1f * px))
                                    drawRect(Color(0xFF4A3121), topLeft = Offset(2f * px, 5.5f * px), size = Size(4f * px, 1f * px))
                                },
                        )
                        Column {
                            Text(
                                text = "ГРАВЕЦЬ_ОДИН",
                                style = MaterialTheme.typography.titleMedium,
                                color = colors.text,
                            )
                            Text(
                                text = "Статус: У мережі",
                                style = MaterialTheme.typography.bodyMedium,
                                color = colors.confirmLight,
                            )
                        }
                    }
                    OreCloseButton(onClick = {})
                }
            }

            OreDivider()

            Section("Кнопки (група з 3 в ряд)") {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    OreButton(
                        text = "Зліва",
                        onClick = {},
                        modifier = Modifier.weight(1f).height(IntrinsicSize.Min),
                    )
                    OreButton(
                        text = "По центру",
                        variant = OreButtonVariant.Gray,
                        onClick = {},
                        modifier = Modifier.weight(1f).height(IntrinsicSize.Min),
                    )
                    OreButton(
                        text = "Справа",
                        variant = OreButtonVariant.Danger,
                        onClick = {},
                        modifier = Modifier.weight(1f).height(IntrinsicSize.Min),
                    )
                }
            }

            OreDivider()

            Section("Поля вводу та селектори") {
                OreTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = "ІМ'Я КОРИСТУВАЧА",
                    placeholder = "Введіть текст...",
                )
                Spacer(modifier = Modifier.height(12.dp))
                OreDropdown(
                    label = "МОВА",
                    options = dropdownOptions,
                    selectedValue = selectedDropdownValue,
                    onValueChange = { selectedDropdownValue = it },
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        OreSwitch(checked = switchChecked, onCheckedChange = { switchChecked = it })
                        Text(
                            text = "ПЕРЕМИКАЧ",
                            style = MaterialTheme.typography.bodyMedium,
                            color = colors.text,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        OreCheckbox(
                            checked = checkboxChecked,
                            onCheckedChange = { checkboxChecked = it }
                        )
                        Text(
                            text = "ЧЕКБОКС",
                            style = MaterialTheme.typography.bodyMedium,
                            color = colors.text,
                        )
                    }
                }
            }

            OreDivider()

            Section("Слайдери") {
                OreSlider(
                    label = "БЕЗПЕРЕРВНА ШКАЛА: ${(sliderValue * 100).toInt()}%",
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                )
                Spacer(modifier = Modifier.height(16.dp))
                OreSlider(
                    label = "КРОКОВА ШКАЛА (ЗУПИНКИ: 0, 25, 75, 100): ${steppedSliderValue.toInt()}%",
                    value = steppedSliderValue,
                    valueRange = 0f..100f,
                    steps = listOf(0f, 25f, 75f, 100f),
                    onValueChange = { steppedSliderValue = it },
                )
            }

            OreDivider()

            Section("Панелі") {
                OreCard {
                    Text(
                        text = "КОНТЕНТ НА ЗНИЖЕНІЙ ПОВЕРХНІ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.text,
                    )
                    OreDivider(Modifier.padding(vertical = 12.dp))
                    OreMenuItem(
                        title = "ПУНКТ МЕНЮ",
                        subtitle = "Детальний опис тут",
                        onClick = {},
                    )
                }
            }
        }
    }
}

@Composable
private fun Section(title: String, content: @Composable ColumnScope.() -> Unit) {
    val colors = LocalOreColors.current
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = title.uppercase(),
            style = OreTypography.Default.labelMedium.copy(color = colors.textMuted),
        )
        content()
    }
}

@Preview(showBackground = true, widthDp = 500, heightDp = 1000)
@Composable
private fun OreUiSamplePreview() {
    OreTheme {
        OreUiSample()
    }
}