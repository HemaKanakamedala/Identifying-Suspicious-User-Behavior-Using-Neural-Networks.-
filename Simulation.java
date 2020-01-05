package com;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.FileWriter;
public class Simulation{
public static void checkBehaviour(){
	StringBuilder sb = new StringBuilder();
	sb.append("user_id,system_id,year,month,day,hour,minute,second,session_duration,latitude,longitude,label"+System.getProperty("line.separator"));
	for(Map.Entry<String,ArrayList<String>> me : GenerateRandomAccount.map.entrySet()){
		String key = me.getKey();
		ArrayList<String> value = me.getValue();
		double non_working_day_activity = 0;
		double multiple_devices = 0;
		double location_count = 0;
		double session = 0;
		HashSet<String> dev = new HashSet<String>();
		HashSet<String> loc = new HashSet<String>();
		for(int i=0;i<value.size();i++){
			String arr[] = value.get(i).split(",");
			dev.add(arr[1]);
			loc.add(arr[9]+","+arr[10]);
			int day = Integer.parseInt(arr[4]);
			int sessions = Integer.parseInt(arr[8]);
			if(sessions < 300)
				session = session + 1;
			if(day == 7 || day == 6)
				non_working_day_activity = non_working_day_activity + 1;
		}
		non_working_day_activity = (non_working_day_activity / 7.0) * 10.0;
		location_count = (double)loc.size()+1;
		location_count = (location_count / 7.0) * 10.0;
		multiple_devices = (double)dev.size();
		int label = 1; 
		System.out.println(non_working_day_activity+" "+location_count+" "+multiple_devices+" "+session);
		if(non_working_day_activity > 3.33 && location_count > 4.0 && multiple_devices > 3 && session >= 2)
			label = 0;
		for(int i=0;i<value.size();i++){
			sb.append(value.get(i)+","+label+System.getProperty("line.separator"));
		}
	}
	try{
		FileWriter fw = new FileWriter("dataset.txt");
		fw.write(sb.toString());
		fw.close();
	}catch(Exception e){
		e.printStackTrace();
	}
}
}