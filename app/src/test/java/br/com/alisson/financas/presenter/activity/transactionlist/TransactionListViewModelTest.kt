package br.com.alisson.financas.presenter.activity.transactionlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.alisson.financas.data.local.repository.finance.FinanceRepository
import br.com.alisson.financas.data.local.repository.transaction.TransactionsRepository
import br.com.alisson.financas.domain.model.Transaction
import br.com.alisson.financas.data.util.FinanceCategory
import br.com.alisson.financas.presenter.activity.home.HomeViewModel
import br.com.alisson.financas.presenter.activity.home.HomeViewState
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
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TransactionListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var transactionListObserver: Observer<HomeViewState>

    private val trRepository = mockk<TransactionsRepository>()
    private val fnRepository = mockk<FinanceRepository>()

    private val viewModel = HomeViewModel(trRepository, fnRepository)

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
    fun `when view model getTransactions get success then sets transactionListLiveData`() = runBlocking {
        // Arrange
        val list = listOf(
            Transaction(1,100.0, "Economia", FinanceCategory.REVENUE.name, Calendar.getInstance())
        )
        val transactionViewState = HomeViewState.ListViewState(false, list)

        coEvery { trRepository.getAllTransactions() } returns list

        viewModel.homeViewState.observeForever(transactionListObserver)


        // Act
        viewModel.getTransactions()


        // Assert
        verify(transactionListObserver).onChanged(transactionViewState)
    }

}