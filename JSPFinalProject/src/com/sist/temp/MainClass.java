package com.sist.temp;

import java.util.Arrays;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        //String s=getRand();
        //System.out.println(s);
		TestDAO dao=new TestDAO();
		for(int i=1;i<=31;i++)
		{
			dao.timeInsert(i, getRand());
		}
		System.out.println("저장 완료");
	}
	public static String getRand()
	{
		String result="";
		int[] com=new int[(int)(Math.random()*6)+5]; // 예약 5~10
		int su=0;
		boolean bCheck=false; // 난수 1~19 => 중복이 없는 난수 
		for(int i=0;i<com.length;i++)
		{
			bCheck=true;// 중복여부 확인 (true(중복)=> while을 다시 수행 , false=com에 저장 
			while(bCheck)
			{
				// 난수 발생 
				su=(int)(Math.random()*19)+1;  
				//      ================== 0~18 ==> +1 => 1~19
				bCheck=false;// 종료 시점
				for(int j=0;j<i;j++)// 같은 숫자가 발생햇는지 여부
				{
					if(com[j]==su) // 중복
					{
						bCheck=true;
						break;
					}
				}
			}
			// 중복된 수가 없다 
			com[i]=su;
		}
		
		Arrays.sort(com);
		for(int i=0;i<com.length;i++)
		{
			result+=com[i]+",";
		}
		result=result.substring(0,result.lastIndexOf(","));
		return result;
	}

}








