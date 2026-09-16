package io.dangerous.oreui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

public object OreShapes {
    public val small = RoundedCornerShape(0.dp)
    public val medium = RoundedCornerShape(0.dp)
    public val large = RoundedCornerShape(0.dp)

    public val material: Shapes = Shapes(
        small = small,
        medium = medium,
        large = large
    )
}
