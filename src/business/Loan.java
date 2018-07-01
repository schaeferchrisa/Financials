
package business;

/**
 *
 * @author schaefer
 */
public class Loan {
    private double pr, rate, mopmt;
    private int term;
    private boolean built;
    private double[] bbal, intchg, ebal;
    
    public Loan() {
        pr = 0;
        rate = 0;
        mopmt = 0;
        term = 0;
        built = false;
    }

    public double getPrin() {
        return pr;
    }

    public void setPrin(double pr) {
        this.pr = pr;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getMopmt() {
        if (!built) {
            buildLoan();
        }
        return mopmt;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
    
    private void buildLoan() {
        // calculate monthly payment
        double mrate = this.rate/12.0; // monthly rate
        double denom = Math.pow((1+mrate), this.term) - 1;
        this.mopmt = (mrate + (mrate/denom)) * this.pr;
        
        
        //now build arrays for schedule display...
        bbal = new double[this.term];
        intchg = new double[this.term];
        ebal = new double[this.term];
        
        bbal[0] = this.pr;
        for (int i=0; i<this.term; i++) {
            if (i>0) {
                bbal[i] = ebal[i-1];
            }
            intchg[i] = bbal[i] * mrate;
            ebal[i] = bbal[i] + intchg[i] - this.mopmt;
        }
	this.built = true;
    }
    
    public double getBegBal(int mo) {
        // month is 1 based (assumption)
        if (!built) {
            buildLoan();
        }
        if (mo < 1 || mo > this.term) {
            return -1;
        }
        return bbal[mo-1];
    }

    public double getIntChg(int mo) {
        // month is 1 based (assumption)
        if (!built) {
            buildLoan();
        }
        if (mo < 1 || mo > this.term) {
            return -1;
        }
        return intchg[mo-1];
    }
    
    public double getEndBal(int mo) {
        // month is 1 based (assumption)
        if (!built) {
            buildLoan();
        }
        if (mo < 1 || mo > this.term) {
            return -1;
        }
        return ebal[mo-1];
    }
}
