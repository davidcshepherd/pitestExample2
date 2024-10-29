package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BankingSystemTest {
    private BankingSystem account1;
    private BankingSystem account2;

    @BeforeEach
    public void setUp() {
        account1 = new BankingSystem("Alice");
        account2 = new BankingSystem("Bob");
    }

    @Test
    public void testInitialBalanceIsZero() {
        assertEquals(0.0, account1.getBalance());
    }

    @Test
    public void testDepositValidAmount() {
        account1.deposit(500);
        assertEquals(500.0, account1.getBalance());
        assertTrue(account1.getTransactionHistory().contains("Deposited: $500.0"));
    }

    @Test
    public void testDepositInvalidAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account1.deposit(-100);
        });
        assertEquals("Deposit amount must be positive!", exception.getMessage());
    }

    @Test
    public void testWithdrawValidAmount() {
        account1.deposit(500);
        account1.withdraw(200);
        assertEquals(300.0, account1.getBalance());
        assertTrue(account1.getTransactionHistory().contains("Withdrew: $200.0"));
    }

    @Test
    public void testWithdrawInvalidAmountExceedsBalance() {
        account1.deposit(100);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account1.withdraw(200);
        });
        assertEquals("Invalid withdrawal amount!", exception.getMessage());
    }

    @Test
    public void testWithdrawInvalidNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account1.withdraw(-50);
        });
        assertEquals("Invalid withdrawal amount!", exception.getMessage());
    }

    @Test
    public void testTransferFundsValid() {
        account1.deposit(500);
        account1.transferFunds(account2, 200);
        assertEquals(300.0, account1.getBalance());
        assertEquals(200.0, account2.getBalance());
        assertTrue(account1.getTransactionHistory().contains("Transferred: $200.0 to Bob"));
        assertTrue(account2.getTransactionHistory().contains("Received: $200.0 from Alice"));
    }

    @Test
    public void testTransferFundsInvalidAmountExceedsBalance() {
        account1.deposit(100);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account1.transferFunds(account2, 200);
        });
        assertEquals("Invalid transfer amount!", exception.getMessage());
    }

    @Test
    public void testTransferFundsInvalidNegativeAmount() {
        account1.deposit(500);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account1.transferFunds(account2, -100);
        });
        assertEquals("Invalid transfer amount!", exception.getMessage());
    }

    @Test
    public void testGetTransactionHistory() {
        account1.deposit(100);
        account1.withdraw(50);
        List<String> history = account1.getTransactionHistory();
        assertTrue(history.contains("Deposited: $100.0"));
        assertTrue(history.contains("Withdrew: $50.0"));
    }

    @Test
    public void testDisplayAccountInfo() {
        account1.deposit(100);
        account1.withdraw(50);
        account1.displayAccountInfo();
        // You can manually check the output or capture it using a different method if needed
    }
}
