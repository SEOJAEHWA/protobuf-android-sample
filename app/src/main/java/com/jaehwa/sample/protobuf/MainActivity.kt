package com.jaehwa.sample.protobuf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jaehwa.sample.protobuf.ui.theme.ProtoBuf_DataStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProtoBuf_DataStoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val count by viewModel.count.collectAsStateWithLifecycle()
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Spacer(modifier = Modifier.height(24.dp))
                        DataStoreTest(
                            count = count,
                            onAddCountClick = viewModel::setCount,
                            onClearCountClick = viewModel::clearCount,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DataStoreTest(
    modifier: Modifier = Modifier,
    count: Int = 0,
    onAddCountClick: (count: Int) -> Unit = {},
    onClearCountClick: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier,
            text = "Count: $count"
        )
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier,
                onClick = { onAddCountClick(count + 1) }
            ) {
                Text(
                    modifier = Modifier,
                    text = "+Count"
                )
            }
            Button(
                modifier = Modifier,
                onClick = onClearCountClick
            ) {
                Text(
                    modifier = Modifier,
                    text = "Clear"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DataStoreTestPreview() {
    ProtoBuf_DataStoreTheme {
        DataStoreTest()
    }
}