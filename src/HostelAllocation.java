import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class HostelAllocation {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter number of students : ");
    Integer n = Integer.parseInt(br.readLine());
    System.out.println("Enter available capacity : ");
    Integer cap = Integer.parseInt(br.readLine());
    String[][] arr = new String[n][3];
    int i = 0;
    while (i < n) {
      String[] data = br.readLine().split(" ");
      arr[i][0] = data[0];
      arr[i][1] = data[1];
      arr[i][2] = data[2];
      i++;
    }
    Map<String,LinkedHashSet<Integer>> resultMap = new HostelAllocation().allocateHostels(arr,n,cap);
    System.out.println("############### Answer ################");
    for(Map.Entry<String,LinkedHashSet<Integer>> entry : resultMap.entrySet()) {
      System.out.println(entry.getKey()+" : "+entry.getValue());
    }
  }

  public Map<String, LinkedHashSet<Integer>> allocateHostels(String[][] arr, int n, int cap) {
    Integer singleHostelCapacity = cap/4;
    System.out.println(singleHostelCapacity);
    HashSet<Integer> registeredStudents = new HashSet<>();
    Map<String, LinkedHashSet<Integer>> map = new LinkedHashMap<>();
    Integer count = 0;
    while(count < n) {
      Integer rollNo = Integer.parseInt(arr[count][0]);
      if(!registeredStudents.contains(rollNo)) {
        String boardingHouse = arr[count][1]+arr[count][2];
        if(map.containsKey(boardingHouse)) {
          LinkedHashSet<Integer> set = map.get(boardingHouse);
          if(set.size() < singleHostelCapacity) {
            set.add(rollNo);
            map.put(boardingHouse,set);
          } else {
            if(map.containsKey("NA")) {
              LinkedHashSet<Integer> notAllocated = map.get("NA");
              notAllocated.add(rollNo);
              map.put("NA", notAllocated);
            } else {
              LinkedHashSet<Integer> notAllocated = new LinkedHashSet<>();
              notAllocated.add(rollNo);
              map.put("NA", notAllocated);
            }
          }
        } else {
          LinkedHashSet<Integer> set = new LinkedHashSet<>();
          set.add(rollNo);
          map.put(boardingHouse,set);
        }
      }
      count++;
    }
    if(!map.containsKey("NA")) {
      map.put("NA", new LinkedHashSet<>());
    }
    return map;
  }

}