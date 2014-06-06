package pm.web.cbr.experiment;

import jcolibri.cbrcore.Attribute;
import jcolibri.datatypes.Instance;

public class Solution  implements jcolibri.cbrcore.CaseComponent {

    Instance mainConcept;
    Instance recommendation;


    public String toString()
    {
        return "("+mainConcept+recommendation+")";
    }

    public Attribute getIdAttribute() {

        return new Attribute("mainConcept", this.getClass());
    }

    /**
     * @return Returns the recommendation.
     */
    public Instance getRecommendation() {
        return recommendation;
    }

    /**
     */
    public void setRecommendation(Instance Recommendation) {
        this.recommendation = Recommendation;
    }

    /**
     * @return Returns the mainConcept.
     */
    public Instance getMainConcept() {
        return mainConcept;
    }

    /**
     */
    public void setMainConcept(Instance mc) {
        this.mainConcept = mc;
    }
}
