package com.example.openinapp.ui.dashboard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine

@Composable
fun LineChartScreen() {
    val steps = 5
    val pointsData = listOf(
        Point(0f, 25f),
        Point(1f, 35f),
        Point(2f, 65f),
        Point(3f, 40f),
        Point(4f, 90f)
    )
    val monthList = listOf("Jan", "Feb", "Mar", "Apr", "May")
    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.Transparent)
        .steps(pointsData.size-1)
        .labelData { i->monthList[i] }
        .labelAndAxisLinePadding(10.dp)
        .axisLineColor(Color.Transparent)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i->
            val yscale = 100/steps
            (i*yscale).toString()
        }
        .axisLineColor(Color.Transparent)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()


    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines =  listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = Color(0xFF0e6fff),
                        lineType = LineType.Straight(isDotted = false)
                    ),
                    IntersectionPoint(
                        color = Color(0xFF3A7BD3)
                    ),
                    SelectionHighlightPoint(color = MaterialTheme.colorScheme.primary),
                    ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF0e6fff),
                                Color.Transparent
                            )
                        )
                    ),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        backgroundColor = MaterialTheme.colorScheme.surface,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = MaterialTheme.colorScheme.outlineVariant)
    )

    LineChart(
        modifier = Modifier.fillMaxWidth().height(300.dp),
        lineChartData = lineChartData
    )
}