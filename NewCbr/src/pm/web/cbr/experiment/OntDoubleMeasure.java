package pm.web.cbr.experiment;

import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import es.ucm.fdi.gaia.ontobridge.OntologyDocument;
import jcolibri.datatypes.Instance;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeepBasic;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDetail;
import jcolibri.util.FileIO;
import jcolibri.util.OntoBridgeSingleton;

import java.util.ArrayList;
import java.util.List;

public class OntDoubleMeasure implements LocalSimilarityFunction
{
    double _interval;

    public OntDoubleMeasure(double interval)
    {
        _interval = interval;
    }

    public double compute(Object caseObject, Object queryObject) throws NoApplicableSimilarityFunctionException
    {
        if ((caseObject == null) || (queryObject == null))
            return 0;
        if (!(caseObject instanceof Instance))
            throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), caseObject.getClass());
        if (!(queryObject instanceof Instance))
            throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), queryObject
                    .getClass());

        Instance i1 = (Instance) caseObject;
        Instance i2 = (Instance) queryObject;

        if (i1.equals(i2))
            return 1;

        OntoBridge ob = OntoBridgeSingleton.getOntoBridge();

        double v1 = Double.parseDouble(getProperty(ob, i1, "hasValue"));
        double v2 = Double.parseDouble(getProperty(ob, i2, "hasValue"));
        Interval interval = new Interval(_interval);

        double res = interval.compute(v1, v2);
        return res;
    }

    /** Applicable to Instance */
    public boolean isApplicable(Object o1, Object o2)
    {
        if ((o1 == null) && (o2 == null))
            return true;
        else if (o1 == null)
            return o2 instanceof Instance;
        else if (o2 == null)
            return o1 instanceof Instance;
        else
            return (o1 instanceof Instance) && (o2 instanceof Instance);
    }

    String getProperty(OntoBridge ob, Instance ins, String name)
    {
        List<String> properties = new ArrayList<String>();
        List<String> values = new ArrayList<String>();
        String str="";

        ob.listInstancePropertiesValues(ins.toString(), properties, values);
        int i = properties.indexOf(ob.getURI(name));
        if (i>-1) str = values.get(i).substring(0,values.get(i).indexOf('^'));
        else {
            str = ins.toString().substring(1).replace('_', '.');
        }

        return str;
    }
}
