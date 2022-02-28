package br.com.alisson.financas.presenter.activity.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.alisson.financas.domain.TransactionFactory
import br.com.alisson.financas.domain.usecase.CreateTransactionUseCase
import br.com.alisson.financas.domain.usecase.GetFinanceUseCase
import br.com.alisson.financas.domain.usecase.GetTransactionsUseCase
import br.com.alisson.financas.domain.usecase.UpdateFinanceUseCase
import com.nhaarman.mockitokotlin2.verify
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var homeViewStateObserver: Observer<HomeViewState>

    private val getTransaction = mockk<GetTransactionsUseCase>()
    private val createTransaction = mockk<CreateTransactionUseCase>()
    private val getFinance = mockk<GetFinanceUseCase>()
    private val updateFinance = mockk<UpdateFinanceUseCase>()

    private val viewModel = HomeViewModel(
        getFinance,
        getTransaction,
        createTransaction,
        updateFinance
    )

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getTransactions_return_success_sets_homeViewState() = runBlocking {

        //GIVEN
        val homeViewState = HomeViewState.ListViewState(false, TransactionFactory.transactions)

        viewModel.homeViewState.observeForever(homeViewStateObserver)

        coEvery { getTransaction() } returns TransactionFactory.transactions


        //WHEN
        viewModel.getTransactions()


        //THEN
        verify(homeViewStateObserver).onChanged(homeViewState)
    }

}