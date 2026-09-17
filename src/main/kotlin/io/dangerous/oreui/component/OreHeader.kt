package io.dangerous.oreui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dangerous.reblink.oreui.R
import io.dangerous.oreui.OreTypography

public data class HeaderTab(
    val icon: ImageVector,
    val isSelected: Boolean = false,
    val onClick: () -> Unit
)

@Composable
public fun OreHeader(
    title: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    tabs: List<HeaderTab> = emptyList(),
    actions: @Composable RowScope.() -> Unit = {}
) {
    // Кольори класичного світлого хедера Minecraft
    val headerBg = Color(0xFFD2D2D2)
    val textColor = Color.Black
    val borderColor = Color(0xFF8b8c8f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(headerBg)
            .border(2.dp, borderColor, RectangleShape)
            .drawBehind {
                val w = 2.dp.toPx()
                // Внутрішня світла грань зверху та зліва
                drawRect(color = Color.White, topLeft = Offset(w, w), size = Size(size.width - 2 * w, w))
                drawRect(color = Color.White, topLeft = Offset(w, w), size = Size(w, size.height - 2 * w))
                // Внутрішня темна грань знизу та справа
                drawRect(color = Color(0xFF585858), topLeft = Offset(w, size.height - 2 * w), size = Size(size.width - 2 * w, w))
                drawRect(color = Color(0xFF585858), topLeft = Offset(size.width - 2 * w, w), size = Size(w, size.height - 2 * w))
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onBack != null) {
                HeaderButton(onClick = onBack) {

                }
            }

            // Вкладки з іконками
            tabs.forEach { tab ->
                Spacer(Modifier.width(4.dp))
                HeaderButton(
                    onClick = tab.onClick,
                    isSelected = tab.isSelected
                ) {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = null,
                        tint = if (tab.isSelected) Color.White else Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(Modifier.width(12.dp))

            Text(
                text = title.uppercase(),
                color = textColor,
                style = OreTypography.Default.titleMedium,
                modifier = Modifier.weight(1f)
            )

            Row(content = actions)
        }
    }
}

@Composable
private fun HeaderButton(
    onClick: () -> Unit,
    isSelected: Boolean = false,
    content: @Composable () -> Unit
) {
    val baseColor = if (isSelected) Color(0xFF7A7A7A) else Color(0xFFC6C6C6)
    
    Box(
        modifier = Modifier
            .size(44.dp)
            .background(Color.Transparent)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.back_button),
            contentDescription = "Close",
            modifier = Modifier.size(18.dp),
        )
    }
}
