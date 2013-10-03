package jetbrains.sample.testSlowDomain;

import java.util.ArrayList;
import java.util.List;

import jetbrains.sample.exception.*;
import jetbrains.sample.statistic.*;

public class SlowTestDetection {

    private static List<TimeExecution> times;
    //private int minimum; //tempo minimo
    private Regression findRegression=null;

    private static List<TimeExecution> SampleBase;
    private static List<TimeExecution> SampleLast;
    private String nameTest=null;
    private long idTest=-1;
    private boolean changeBase=false;
    private static int numericalThreshold=0;
    private static int percentualThreshold=0;


    public SlowTestDetection(List<TimeExecution> times){
        this.times = times;
    }

    public SlowTestDetection(List<TimeExecution> times, String nameTest, long idTest){
        this.times = times;
        this.nameTest=nameTest;
        this.idTest=idTest;

    }

    public Regression findRegressionId(int SizeStart, int SizeEnd, int numericalThreshold, int percentualThreshold) throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime {
          this.numericalThreshold=numericalThreshold;
          this.percentualThreshold=percentualThreshold;
          return findRegressionId(SizeStart,SizeEnd);
    }
    //ExecutionsSample almeno 6-7 elementi, direi 10..(esecuzioni per costruire il campione)
    //ExecutionsFindRegression direi almeno 3, direi 5-6..(esecuzioni per trovare la regressione)
    public Regression findRegressionId(int SizeStart, int SizeEnd) throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime {

        SampleBase= new ArrayList<TimeExecution>();
        SampleLast = new ArrayList<TimeExecution>();


        if(times.size()<=2)
            return null;

        if(SizeStart<=5 || SizeStart>20){
            throw new IllegalExecutionsSampleBasis();
        }
        if(SizeEnd<=2 || SizeEnd>20){
            throw new IllegalExecutionsSampleLast();
        }

        int posEndBase;
        int positionSampleLast=0;

        if(times.size()>= SizeStart+SizeEnd){
            SampleBase = new ArrayList<TimeExecution>();
            posEndBase = Build_Sample(SampleBase, 0, times.size()-1, SizeStart);

            //change base
            int anotherposition=posEndBase;

            if(SampleBase.size()<SizeStart)
                return null;

            //E' lento formare anotherSample tutte le volte!
            //basta che anotherSample ha un esecuzione minima minore, e che è minore stocasticamente e possiamo cambiare

            if(changeBase){
                int minimum = findMin(SampleBase);
                posEndBase = changeBase(minimum, posEndBase, SizeStart);
            }

            SampleLast = new ArrayList<TimeExecution>();
            if(times.size()-1 > posEndBase)
                Build_Sample(SampleLast, times.size()-1,posEndBase, SizeEnd);
            //System.out.println("Size SampleLast"+SampleLast.size());

        }

        if(SampleLast.size()==SizeEnd && SampleBase.size()==SizeStart){
            try {
                int minLast  = findMin(SampleLast);
                int minBase = findMin(SampleBase);
                if(minLast<0)
                    throw new IllegalTime();

                List<Integer> bestExecution=new ArrayList<Integer>();
                for(int i=0;i<10;i++)
                    bestExecution.add(minLast);

                MannWhitney Mnw=new MannWhitney(convert(SampleBase), bestExecution, AlfaPercentage.ONE);
                if(Mnw.isMajor(1) && surpassThreshold(minLast, minBase))
                    findRegression=new Regression(nameTest,idTest);
            } catch (IllegalSizeSamples e) {
                new IllegalSizeSamples();
            }
        }

        return findRegression;


    }

    private static boolean surpassThreshold(int minLast, int minBase){
        System.out.println(numericalThreshold);
        System.out.println(percentualThreshold);
        System.out.println("minLast: "+minLast);
        System.out.println("minBase: "+minBase);
        double percentualLimit=minBase+((double)minBase/100)*percentualThreshold;

        int numericalLimit=minBase+numericalThreshold;
        System.out.println("percentualLimit:" +percentualLimit);
        System.out.println("numericalLimit:" +numericalLimit);
        if((minLast>numericalLimit) && (minLast>percentualLimit)){

            System.out.println(true);
            return true;
        }
        else{
            System.out.println(false);
            return false;
        }
    }
    private static int changeBase(int minimum, int posEndBase, int SizeStart) throws IllegalTime {
        int anotherposition;
        for(int i=posEndBase; i<times.size(); i++){

            if(times.get(i).getTime() >= minimum) //la durata non è inferiore al minimo, vai avanti
                continue; //velocizza
            else{

                List<TimeExecution> anotherSample = new ArrayList<TimeExecution>();
                anotherposition = Build_Sample(anotherSample, i, times.size()-1, SizeStart);
                //l'esecuzione minima non viene trascurata (BoxplotBase)
				/*for(int i=0;i<anotherSample.size();i++){
					
				}*/
                if(anotherSample.size()==SizeStart && SampleBase.size()==SizeStart){
                    //System.out.println("confronto");
                    for(int z=0;z<anotherSample.size();z++){
                        System.out.print(anotherSample.get(z)+"; ");
                    }
                    MannWhitney Mnw;
                    try {
                        Mnw = new MannWhitney(convert(SampleBase), convert(anotherSample), AlfaPercentage.ONE);
                        if(Mnw.isMajor(0)){ //se il SampleBase è maggiore
                            SampleBase.clear();
                            SampleBase.addAll(anotherSample);
                            posEndBase = anotherposition;
                            minimum = findMin(SampleBase);
                            if(minimum<0)
                                throw new IllegalTime();
                        }

                    } catch (IllegalSizeSamples e) {
                        e.printStackTrace();
                    }
                }

            }

        }
        return posEndBase;

    }


    private static int Build_Sample(List<TimeExecution> sample, int start, int limitEnd, int sizeSample){

        int i=start;
        int limitSize=sizeSample;

        if(start<limitEnd){
            int pos=addElementsSampleBase(times, start, limitEnd, sample, sizeSample, sizeSample);
            return pos;

        }else{
            int pos=addElementsSampleLast(times,start, limitEnd, sample, sizeSample, sizeSample);
            return pos;
        }

    }

    private static int addElementsSampleBase(List<TimeExecution> tests, int start, int limitEnd, List<TimeExecution> sample, int sizeSample,
                                             int numberElements) {

        List<TimeExecution> correctSample=new ArrayList<TimeExecution>();
        int i=start;
        for(i=start;i<=limitEnd;i++){
            sample.add(tests.get(i));
            if(sample.size()>=numberElements){

                correctSample.clear();
                correctSample=getCorrectSampleBase(sample);
                //correctSample=getCorrectSample(sample);

                if(i==limitEnd){ //se ha raggiunto il limite
                    copySample(sample, correctSample, sizeSample);
                    return i+1;
                }
                if(correctSample.size()<sizeSample){
                    //sample.clear();
                    //i=start-1;
                    numberElements++;
                    continue;
                }
                //System.out.println(correctSample.size());
                if(correctSample.size()>=sizeSample){
                    copySample(sample, correctSample, sizeSample);
                    return i+1;
                }
            }

        }
        return i+1;

    }

    private static int addElementsSampleLast(List<TimeExecution> tests, int start, int limitEnd, List<TimeExecution> sample, int sizeSample,
                                             int numberElements) {

        List<TimeExecution> correctSample=new ArrayList<TimeExecution>();
        int i=start;

        for(i=start;i>=limitEnd;i--){
            sample.add(tests.get(i));

            if(sample.size()>=numberElements){
                correctSample.clear();
                correctSample=getCorrectSample(sample);

                if(i==limitEnd){ //se ha raggiunto il limite
                    copySample(sample, correctSample, sizeSample);
                    return i-1;
                }

                if(correctSample.size()<sizeSample){
                    //continua e incrementa numberElements;
                    //sample.clear();
                    //i=start-1;
                    numberElements++;
                    continue;
                }

                if(correctSample.size()>=sizeSample){
                    copySample(sample, correctSample, sizeSample);
                    return i-1;
                }
            }

        }
        return i-1;


    }


    private static void copySample(List<TimeExecution> sample, List<TimeExecution> correctSample, int sizeSample) {
        sample.clear();
        int size=sizeSample;

        if(correctSample.size()<sizeSample)
            size=correctSample.size();

        for(int k=0;k<size;k++){
            sample.add(correctSample.get(k));
        }
    }

    private static List<TimeExecution> getCorrectSampleBase(List<TimeExecution> sample) {
        List<TimeExecution> correctSample=new ArrayList<TimeExecution>();
        try{
            BoxPlot box=new BoxPlot(sample, 1);
            correctSample.clear();
            correctSample.addAll(box.getCorrectSampleTimeExecution());
        } catch (IllegalSizeSamples e) {
            new IllegalSizeSamples();
        }
        return correctSample;
    }

    private static List<TimeExecution> getCorrectSample(List<TimeExecution> sample) {
        List<TimeExecution> correctSample=new ArrayList<TimeExecution>();
        try{
            BoxPlot box=new BoxPlot(sample, 0);
            correctSample.clear();
            correctSample.addAll(box.getCorrectSampleTimeExecution());
        } catch (IllegalSizeSamples e) {
            new IllegalSizeSamples();
        }
        return correctSample;
    }

    /*
    private static int findPointRegression(int start, int end) {
        int j=0;
        int point=-1;
		/*

        return point;

    }
    */


    private static int findMin(List<TimeExecution> sample) {
        int min = -1;
        if(sample.size()>0)
            min = sample.get(0).getTime();
        for(int i=0;i<sample.size();i++){
            if(sample.get(i).getTime()<min)
                min=sample.get(i).getTime();
        }
        return min;
    }

    public List<TimeExecution> getSampleBase(){
        return this.SampleBase;
    }

    public List<TimeExecution> getSampleLast(){
        return this.SampleLast;
    }


    public List<TimeExecution> getTests() {
        return times;
    }

    public static List<Integer> convert(List<TimeExecution> sample){
        List<Integer> sampleInteger=new ArrayList<Integer>();
        for(int i=0;i<sample.size();i++)
            sampleInteger.add(sample.get(i).getTime());

        return sampleInteger;

    }

    public void setTrueChangeBase(){
       this.changeBase=true;
    }

    public void setFalseChangeBase(){
       this.changeBase=false;
    }


}
