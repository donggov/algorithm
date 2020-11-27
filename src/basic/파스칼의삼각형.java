package basic;

public class 파스칼의삼각형 {

    public static void main(String[] args) {
        int n = 100;
        int pas[][] = new int[n][n];
        for (int i=1; i<n; i++) {
            // 하나도 안고리는 경우, 모두 고르는 경우는 1로 초기화
            pas[i][0] = pas[i][i] = 1;
            for (int j=1; j<i; j++) {
                pas[i][j] = pas[i-1][j] + pas[i-1][j-1];
            }
        }

        System.out.println(pas[3][1]);
        System.out.println(pas[3][2]);
        System.out.println(pas[3][3]);
        System.out.println(pas[5][2]);
        System.out.println(pas[6][2]);
    }

}
