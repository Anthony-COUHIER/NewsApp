package anthony.project.news.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import anthony.project.news.domain.usecase.GetAllNewsUseCase
import anthony.project.news.domain.usecase.GetTopHeadlinesUseCase
import anthony.project.news.ui.UiConstant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import java.util.Locale

@KoinViewModel
class HomeViewModel(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    getAllNewsUseCase: GetAllNewsUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    val allNewsPagingFlow = getAllNewsUseCase(Locale.getDefault().language).cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            getTopHeadlinesUseCase(
                country = Locale.getDefault().country,
                pageSize = UiConstant.HomeScreen.HeadlineArticleListSize,
            )
                .filter { it.title.isNotBlank() && it.description?.isNotBlank() == true }
                .let { articles ->
                    _uiState.value = _uiState.value.copy(
                        headlineNews = articles
                    )
                }
        }

        viewModelScope.launch {

        }
    }
}