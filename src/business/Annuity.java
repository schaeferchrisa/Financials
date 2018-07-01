

package business;

/**
 *
 * @author cschaefer
 */
public class Annuity {
    private double amount, rate;
    private int term;
    private boolean built;
    private double[] bbal, iearn, ebal;
    
    public Annuity() {
        this.amount = 0;
        this.rate = 0;
        this.term = 0;
        this.built = false;
    }
    
    public void setDeposit(double a) {
        this.amount = a;
        this.built = false;
    }
    
    public double getDeposit() {
        return this.amount;
    }
    
    public void setRate(double r) {
        this.rate = r;
        this.built = false;
    }
    
    public double getRate() {
        return this.rate;
    }
    
    public void setTerm(int t) {
        this.term = t;
        this.built = false;
    }
    
    public int getTerm() {
        return this.term;
    }
    public double getFinalValue() {
        if (!this.built) {
            buildAnnuity();
        }
        return this.ebal[term-1];
    }
    
    private void buildAnnuity() {
        bbal = new double[this.term];
        iearn = new double[this.term];
        ebal = new double[this.term];
        
        bbal[0] = 0;
        for (int i=0; i<this.term; i++) {
            if (i>0) {
                bbal[i] = ebal[i-1];
            }
            iearn[i] = ((bbal[i] + this.amount) * (this.rate/12.0));
            ebal[i] = bbal[i] + iearn[i] + this.amount;
        }
        this.built = true;
    }
    
    public double getBegBal(int mo) {
        // month is 1 based (assumption)
        if (!this.built) {
            buildAnnuity();
        }
        if (mo < 1 || mo > this.term) {
            return -1;
        }
        return bbal[mo-1];
    }

    public double getIntEarn(int mo) {
        // month is 1 based (assumption)
        if (!this.built) {
            buildAnnuity();
        }
        if (mo < 1 || mo > this.term) {
            return -1;
        }
        return iearn[mo-1];
    }
    
    public double getEndBal(int mo) {
        // month is 1 based (assumption)
        if (!this.built) {
            buildAnnuity();
        }
        if (mo < 1 || mo > this.term) {
            return -1;
        }
        return ebal[mo-1];
    }
}
