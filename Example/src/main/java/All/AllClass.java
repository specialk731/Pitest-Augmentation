package All;

public class AllClass {

    public int ABSMethodInt(int i){
        int j = i;
        return j;
    }

    public double ABSMethodDouble(double d){
        double e = d;
        return e;
    }

    public int AODMethod1(int i){
        int j = i + 0;
        return j;
    }

    public int AODMethod2(int i){
        int j = i + i;
        return j;
    }

    public long AODMethod1Long(long l){
        long m = l + 0;
        return m;
    }

    public long AODMethod2Long(long l){
        long m = l + l;
        return m;
    }

    public int AORMethod(int i, int j, int k, int l, int m, int n){
        int ret = i + j;
        ret = ret - k;
        ret = ret * l;
        ret = ret / m;
        ret = ret % n;
        return ret;
    }

    public long AORMethod2(long l){
        long m = l + 0;
        m = m - 0;
        m = m * 1;
        return m;
    }

    public int OBBNMethod(int i){
        int j = (i & 0);
        return j;
    }

    public boolean OBBNMethod2(int i){
        if((i | 0) == 1)
            return true;
        else
            return false;
    }

    public int RORMethod(int i) {
        int a = 1, b = 2, c = 3, d = 4, e = 5;

        if(i > 0 && i < 2)
            return a;

        if(i >= 2 && i <= 2)
            return b;

        if(i == 3 && i != -1)
            return 3;

        return -1;
    }

    public int UOIMethod(int i){
        int j = i;
        j++;
        j--;
        ++j;
        --j;
        return j;
    }
}
