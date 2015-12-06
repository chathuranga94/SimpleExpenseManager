package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DBHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.InDBAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.InDBTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;

/**
 * Created by NADUN on 12/5/2015.
 */
public class InDBExpenseManager extends ExpenseManager {
    DBHelper dbHelper = null;

    public InDBExpenseManager(Context context) {
        try {

            dbHelper = DBHelper.doSingleton(context);
            setup();


        } catch (ExpenseManagerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setup() throws ExpenseManagerException {
        TransactionDAO inDBTransactionDAO = new InDBTransactionDAO(dbHelper);
        setTransactionsDAO(inDBTransactionDAO);

        AccountDAO inDBAccountDAO = new InDBAccountDAO(dbHelper);
        setAccountsDAO(inDBAccountDAO);

        // Dummy Data
        Account sampleAcct1 = new Account("10001", "BoC", "Saman Fernando", 12000.0);
        Account sampleAcct2 = new Account("20134", "HNB", "Jagath Silva", 25000.0);
        Account sampleAcct3 = new Account("34024", "ComBank", "Nadun Tennakoon", 18000.0);
        getAccountsDAO().addAccount(sampleAcct1);
        getAccountsDAO().addAccount(sampleAcct2);
        getAccountsDAO().addAccount(sampleAcct3);
    }
}
