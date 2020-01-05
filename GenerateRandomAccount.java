package com;
import java.util.Random;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
import javax.swing.table.DefaultTableModel;
public class GenerateRandomAccount{
	static ArrayList<String> username = new ArrayList<String>();
	static ArrayList<String> password = new ArrayList<String>();
	static ArrayList<Double> latitude = new ArrayList<Double>();
	static ArrayList<Double> longitude = new ArrayList<Double>();
	static LinkedHashMap<String,ArrayList<String>> map = new LinkedHashMap<String,ArrayList<String>>();
	static String credential[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9"};
public static void readLocation(){
	try{
		latitude.clear();
		longitude.clear();
		map.clear();
		BufferedReader br = new BufferedReader(new FileReader("latlon.txt"));
		String line = null;
		while((line = br.readLine())!=null){
			line = line.trim();
			if(line.length() > 0){
				String arr[] = line.split(",");
				double lat = Double.parseDouble(arr[0].trim());
				double lon = Double.parseDouble(arr[1].trim());
				if(!latitude.contains(lat))
					latitude.add(lat);
				else
					System.out.println("duplicate latitude : "+lat);
				if(!longitude.contains(lon))
					longitude.add(lon);
				else
					System.out.println("duplicate longitude : "+lon);
			}
		}
		br.close();
	}catch(Exception e){
		e.printStackTrace();
	}
}
public static String getRandomChar(){
	Random r = new Random();
	return credential[r.nextInt(credential.length)];
}
public static void generateAccount(){
	username.clear();
	password.clear();
	for(int i=0;i<latitude.size();i++){
		StringBuilder sb = new StringBuilder();
		for(int j=0;j<10;j++){
			sb.append(getRandomChar());
		}
		String str = sb.toString().trim();
		if(!username.contains(str))
			username.add(str);
		else
			i = i - 1;
	}
	for(int i=0;i<latitude.size();i++){
		StringBuilder sb = new StringBuilder();
		for(int j=0;j<10;j++){
			sb.append(getRandomChar());
		}
		String str = sb.toString().trim();
		if(!password.contains(str))
			password.add(str);
		else
			i = i - 1;
	}
}
public static void createUsers(DefaultTableModel dtm){
	StringBuilder sb = new StringBuilder();
	sb.append("user_id,system_id,username,password,latitude,longitude"+System.getProperty("line.separator"));
	for(int i=0;i<latitude.size();i++){
		String user = "U"+(i+1);
		String system = ""+(i+1);
		sb.append(user+","+system+","+username.get(i)+","+password.get(i)+","+latitude.get(i)+","+longitude.get(i)+System.getProperty("line.separator"));
		Object row[] = {user,system,username.get(i),password.get(i),latitude.get(i),longitude.get(i)};
		dtm.addRow(row);
	}
	try{
		FileWriter fw = new FileWriter("user.txt");
		fw.write(sb.toString().trim());
		fw.close();
	}catch(Exception e){
		e.printStackTrace();
	}
}

public static int getYear(){
	java.util.Date date = new java.util.Date();
	return 2017;
}
public static int getMonth(){
	java.util.Date date = new java.util.Date();
	return (date.getMonth() + 1);
}
public static String getBenignStartTime(){
	Random rn = new Random();
	int range = 23 - 7 + 1;
	int hour = rn.nextInt(range) + 7;

	int min = rn.nextInt(60);
	int seconds = rn.nextInt(60);
	StringBuilder sb = new StringBuilder();
	if(hour <= 9)
		sb.append("0"+hour+":");
	else
		sb.append(hour+":");

	if(min <= 9)
		sb.append("0"+min+":");
	else
		sb.append(min+":");

	if(seconds <= 9)
		sb.append("0"+seconds);
	else
		sb.append(seconds);

	return sb.toString().trim();
}

public static String getSuspiciousStartTime(){
	Random rn = new Random();
	int range = 24 - 1 + 1;
	int hour = rn.nextInt(range) + 1;

	int min = rn.nextInt(60);
	int seconds = rn.nextInt(60);
	StringBuilder sb = new StringBuilder();
	if(hour <= 9)
		sb.append("0"+hour+":");
	else
		sb.append(hour+":");

	if(min <= 9)
		sb.append("0"+min+":");
	else
		sb.append(min+":");

	if(seconds <= 9)
		sb.append("0"+seconds);
	else
		sb.append(seconds);

	return sb.toString().trim();
}

public static int getDay(){
	Random r = new Random();
	int day = r.nextInt(7);
	day = day + 1;
	return day;
}

public static int getBenignSession(){
	Random rn = new Random();
	int range = 600 - 300 + 1;
	return rn.nextInt(range) + 300;
}

public static int getSuspiciousSession(){
	Random rn = new Random();
	int range = 600 - 10 + 1;
	return rn.nextInt(range) + 10;
}

public static int getAccess(){
	Random rn = new Random();
	int range = 5 - 1 + 1;
	return rn.nextInt(range) + 1;
}

public static String getSystem(){
	Random r = new Random();
	return (r.nextInt(20)+1)+"";
}
public static void createSimulationData(){
	ArrayList<String> list = new ArrayList<String>();
	ViewDataset vd = new ViewDataset();
	try{
		BufferedReader br = new BufferedReader(new FileReader("user.txt"));
		String line = br.readLine();
		while((line = br.readLine())!=null){
			line = line.trim();
			if(line.length() > 0){
				list.add(line);
			}
		}
		br.close();
		for(int i=0;i<list.size();i++){
			String arr[] = list.get(i).split(",");
			int access = getAccess();
			int total = 10 - access;
			int k = 0;
			if(i > 0 && i < list.size())
				k = k + 1;
			if(i == 0)
				k = list.size() -1;
			if(i == (list.size() - 1))
				k = 0;
			String temp[] = list.get(k).split(",");
			for(int j=0;j<total;j++){
				int year = getYear();
				int month = getMonth();
				int day = getDay();
				String start[] =  getBenignStartTime().split(":");
				String hour = start[0];
				String min = start[1];
				String sec = start[2];
				int session_duration = getBenignSession();
				String user = arr[0];
				String system = arr[1];
				String lat = arr[4];
				String lon = arr[5];
				String data = user+","+system+","+year+","+month+","+day+","+hour+","+min+","+sec+","+session_duration+","+lat+","+lon;
				String row[] = data.split(",");
				vd.dtm.addRow(row);
				if(map.containsKey(user)){
					map.get(user).add(data);
				}else{
					ArrayList<String> temps = new ArrayList<String>();
					temps.add(data);
					map.put(user,temps);
				}
			}
			for(int j=total;j<10;j++){
				int year = getYear();
				int month = getMonth();
				int day = getDay();
				String start[] =  getSuspiciousStartTime().split(":");
				String hour = start[0];
				String min = start[1];
				String sec = start[2];
				int session_duration = getSuspiciousSession();
				String user = arr[0];
				String system = getSystem();
				String lat = temp[4];
				String lon = temp[5];
				String data = user+","+system+","+year+","+month+","+day+","+hour+","+min+","+sec+","+session_duration+","+lat+","+lon;
				if(map.containsKey(user)){
					map.get(user).add(data);
				}else{
					ArrayList<String> temps = new ArrayList<String>();
					temps.add(data);
					map.put(user,temps);
				}
			}
		}
		vd.setVisible(true);
		vd.setSize(800,600);
	}catch(Exception e){
		e.printStackTrace();
	}
}
}