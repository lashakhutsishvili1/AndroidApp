package com.example.androidtask


open class Account(val accountNumber: String, var ownerName: String) {
    private var balance: Double = 0.0
    fun getBalance(): Double {
        return balance
    }

    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("depoziti warmatebit shesrulda: $amount GEL. axali balansi: $balance GEL")
        } else {
            println("Shecdoma: tanxa unda ikos 0-ze meti.")
        }
    }

    open fun withdraw(amount: Double) {
        if (amount > 0 && balance >= amount) {
            balance -= amount
            println("gamotana warmatebit shesrulda: $amount GEL darchenili balansi: $balance GEL")
        } else {
            println("operacia ver shesrulda: arasakmarisi tanxa.")
        }
    }

    fun printInfo() {
        println("angarishi $accountNumber | mflobeli: $ownerName | balansi: $balance GEL")
    }
}


class SavingsAccount(accountNumber: String, ownerName: String) :
    Account(accountNumber, ownerName) {

    override fun withdraw(amount: Double) {
        if (amount > 500) {
            println("shecdoma: ert tranzaqciashi maksimaluri gamotana aris 500 GEL.")
        } else {
            super.withdraw(amount)
        }
    }
}


class VIPAccount(
    accountNumber: String, ownerName: String, private val transactionFee: Double = 2.0) :
    Account(accountNumber, ownerName) {

    override fun withdraw(amount: Double) {
        val totalAmount = amount + transactionFee
        if (getBalance() >= totalAmount) {
            println("VIP gamotana: $amount GEL + sakomisio $transactionFee GEL")
            super.withdraw(totalAmount)
        } else {
            println("operacia ver shesrulda: balansi arasakmarisia (sachiroa $totalAmount GEL).")
        }
    }
}


fun main() {
    val acc1 = SavingsAccount("SAVING101", "Lasha kh.")
    val acc2 = VIPAccount("VIP202", "Davit B.")

    println("\n=== Savings Account ===")
    acc1.deposit(1000.0)
    acc1.withdraw(300.0)
    acc1.withdraw(600.0)

    println("\n=== VIP Account ===")
    acc2.deposit(1000.0)
    acc2.withdraw(50.0)
    acc2.printInfo()

    println("\n=== polymorphism ===")
    val accounts: List<Account> = listOf(acc1, acc2)
    for (account in accounts) {
        account.deposit(50.0)
        account.printInfo()
    }
}


