package pm.web.cbr.experiment;

import jcolibri.cbrcore.Attribute;
import jcolibri.datatypes.Instance;

public class Description implements jcolibri.cbrcore.CaseComponent {

    public enum ExperienceTypes  { None, Small, Middle, Large };
    public enum DynamicsTypes { None, Negative, Neutral, Positive };
    public enum LeadershipTypes { None, Backward, Middle, Leader };

    Instance  	mainConcept;

    Instance 	experience;
    Instance  	individualPlanSum;
    Instance  	individualPlanDone;
    Instance  	individualPlan;
    Instance  	receiptSum;
    Instance  	receiptNum;
    Instance  	receiptQuantity;

    Instance  	individualPlan_min;
    Instance  	receiptSum_min;
    Instance  	receiptQuantity_min;

    Instance 	period;
    Instance 	worker;

    public String toString()
    {
        return "("+mainConcept+";"+individualPlanSum+";"+individualPlanDone+";"+individualPlan+";"+receiptSum+";"+receiptNum+";"+receiptQuantity+";"+individualPlan_min+";"+receiptSum_min+";"+receiptQuantity_min+";"+experience+";"+period+";"+worker+")";
    }

    /**
     * @return the mainConcept
     */
    public Instance getMainConcept() {
        return mainConcept;
    }
    /**
     */
    public void setMainConcept(Instance mc) {
        this.mainConcept = mc;
    }

    /**
     * @return the receiptSum
     */
    public Instance getReceiptSum() {
        return receiptSum;
    }
    /**
     */
    public void setReceiptSum(Double ReceiptSum) {
        try {receiptSum = Instance.createInstance("v" + ReceiptSum.toString().replace('.', '_'), "ReceiptSum");}
        catch (Exception e) {}
    }
    /**
     */
    public void setReceiptSum(Instance ReceiptSum) {
        receiptSum = ReceiptSum;
    }
    /**
     * @return the receiptNum
     */
    public Instance getReceiptNum() {
        return receiptNum;
    }
    /**
     */
    public void setReceiptNum(Double ReceiptNum) {
        try {receiptNum = Instance.createInstance("v" + ReceiptNum.toString().replace('.', '_'),"ReceiptNum");}
        catch (Exception e) {}
    }
    /**
     */
    public void setReceiptNum(Instance ReceiptNum) {
        receiptNum = ReceiptNum;
    }

    /**
     * @return the receiptQuantity
     */
    public Instance getReceiptQuantity() {
        return receiptQuantity;
    }
    /**
     */
    public void setReceiptQuantity(Double ReceiptQuantity) {
        try {receiptQuantity = Instance.createInstance(("v" + ReceiptQuantity.toString().replace('.', '_')), "ReceiptQuantity");}
        catch (Exception e) {}
    }
    /**
     */
    public void setReceiptQuantity(Instance ReceiptQuantity) {
        receiptQuantity = ReceiptQuantity;
    }

    /**
     * @return the individualPlanSum
     */
    public Instance getIndividualPlanSum() {
        return individualPlanSum;
    }
    /**
     */
    public void setIndividualPlanSum(Double IndividualPlanSum) {
        try {individualPlanSum = Instance.createInstance("v" + IndividualPlanSum.toString().replace('.', '_'),"IndividualPlanSum");}
        catch (Exception e) {}
    }
    /**
     */
    public void setIndividualPlanSum(Instance IndividualPlanSum) {
        individualPlanSum = IndividualPlanSum;
    }

    /**
     * @return the individualPlanDone
     */
    public Instance getIndividualPlanDone() {
        return individualPlanDone;
    }
    /**
     */
    public void setIndividualPlanDone(Double IndividualPlanDone) {
        try {individualPlanDone = Instance.createInstance("v" + IndividualPlanDone.toString().replace('.', '_'),"IndividualPlanDone");}
        catch (Exception e) {}
    }
    /**
     */
    public void setIndividualPlanDone(Instance IndividualPlanDone) {
        individualPlanDone = IndividualPlanDone;
    }

    /**
     * @return the individualPlan
     */
    public Instance getIndividualPlan() {
        return individualPlan;
    }
    /**
     */
    public void setIndividualPlan(Double IndividualPlan) {
        try {individualPlan = Instance.createInstance("v" + IndividualPlan.toString().replace('.', '_'),"IndividualPlan");}
        catch (Exception e) {}
    }
    /**
     */
    public void setIndividualPlan(Instance IndividualPlan) {
        individualPlan = IndividualPlan;
    }

    /**
     * @return the receiptSum_min
     */
    public Instance getReceiptSum_min() {
        return receiptSum_min;
    }
    /**
     */
    public void setReceiptSum_min(Double ReceiptSumMin) {
        try {receiptSum_min = Instance.createInstance("v" + ReceiptSumMin.toString().replace('.', '_'),"ReceiptSumMin");}
        catch (Exception e) {}
    }
    /**
     */
    public void setReceiptSum_min(Instance ReceiptSumMin) {
        receiptSum_min = ReceiptSumMin;
    }

    /**
     * @return the receiptQuantity_min
     */
    public Instance getReceiptQuantity_min() {
        return receiptQuantity_min;
    }
    /**
     */
    public void setReceiptQuantity_min(Double ReceiptQuantity) {
        try {receiptQuantity_min = Instance.createInstance("v" + ReceiptQuantity.toString().replace('.', '_'),"ReceiptQuantityMin");}
        catch (Exception e) {}
    }
    /**
     */
    public void setReceiptQuantity_min(Instance ReceiptQuantityMin) {
        receiptQuantity_min = ReceiptQuantityMin;
    }

    /**
     * @return the individualPlan_min
     */
    public Instance getIndividualPlan_min() {
        return individualPlan_min;
    }
    /**
     */
    public void setIndividualPlan_min(Double IndividualPlanMin) {
        try {individualPlan_min = Instance.createInstance("v" + IndividualPlanMin.toString().replace('.', '_'),"IndividualPlanMin");}
        catch (Exception e) {}
    }
    /**
     */
    public void setIndividualPlan_min(Instance IndividualPlanMin) {
        individualPlan_min = IndividualPlanMin;
    }

    /**
     * @return the experience
     */
    public Instance getExperience() {
        return experience;
    }
    /**
     */
    public void setExperience(Instance Experience) {
        experience = Experience;
    }


    /**
     * @return the period
     */
    public Instance getPeriod() {
        return period;
    }
    /**
     */
    public void setPeriod(Instance Period) {
        period = Period;
    }

    /**
     * @return the worker
     */
    public Instance getWorker() {
        return worker;
    }
    /**
     */
    public void setWorker(Instance Worker) {
        worker = Worker;
    }


    // !!!!!
    @Override
    public Attribute getIdAttribute() {
        return new Attribute("mainConcept", this.getClass());
    }
}
