import java.util.*;
import java.text.DecimalFormat;

class Satellite {
    private String COSPARID;
    private String name;
    private double orbit;
    private boolean status=false;

    // Constructor, getters and setters
    public Satellite(String ID, String name, double orbit, boolean status) {
        super();
        COSPARID = ID;
        this.name = name;
        this.orbit = orbit;
        this.status = status;
    }

    public String getCOSPARID() {
        return COSPARID;
    }

    public void setCOSPARID(String ID) {
        COSPARID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrbit() {
        // add 0
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(orbit);
    }

    public double getOrbitNum() {
        return orbit;
    }

    public void setOrbit(double orbit) {
        this.orbit = orbit;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

class SatelliteManager {
    private List<Satellite> satelliteList;

    public SatelliteManager() {
        this.satelliteList = new ArrayList<>(10);
    }

    // Methods for add, edit, delete, search, block and activate satellites
    public void addSatellite(Satellite satellite) {
        satelliteList.add(satellite);
    }

    public void editSatellite(String COSPARID, Satellite satellite) {
        for (int i = 0; i < satelliteList.size(); i++) {
            if (satelliteList.get(i).getCOSPARID().equals(COSPARID)) {
                satelliteList.set(i, satellite);
                break;
            }
        }
    }

    public void deleteSatellite(String COSPARID) {
        for (int i = 0; i < satelliteList.size(); i++) {
            if (satelliteList.get(i).getCOSPARID().equals(COSPARID)) {
                satelliteList.remove(i);
                break;
            }
        }
    }

    public Satellite searchSatellite(String COSPARID) {
        for (int i = 0; i < satelliteList.size(); i++) {
            if (satelliteList.get(i).getCOSPARID().equals(COSPARID)) {
                return satelliteList.get(i);
            }
        }
        return null;
    }

    public void blockSatellite(String COSPARID) {
        for (int i = 0; i < satelliteList.size(); i++) {
            if (satelliteList.get(i).getCOSPARID().equals(COSPARID)) {
                satelliteList.get(i).setStatus(false);
                break;
            }
        }
    }

    public void activateSatellite(String COSPARID) {
        for (int i = 0; i < satelliteList.size(); i++) {
            if (satelliteList.get(i).getCOSPARID().equals(COSPARID)) {
                satelliteList.get(i).setStatus(true);
                break;
            }
        }
    }

    public void searchSatelliteByName(String name) {
        List<Satellite> searchResult = new ArrayList<>();
        for (int i = 0; i < satelliteList.size(); i++) {
            if (satelliteList.get(i).getName().contains(name)) {
                searchResult.add(satelliteList.get(i));
            }
        }
        showSatelliteList(searchResult);
    }

    public void showActiveSatellites() {
        System.out.println("=============================================");
        System.out.println("COSPARID           卫星名称  轨道半径  是否可用");
        for (int i = 0; i < satelliteList.size(); i++) {
            if (satelliteList.get(i).isStatus()) {
                System.out.println(satelliteList.get(i).getCOSPARID() + "           " + satelliteList.get(i).getName() + "     "
                        + satelliteList.get(i).getOrbit() + "     " + satelliteList.get(i).isStatus());
            }
        }
        System.out.println("=============================================");
    }

    public void showBlockedSatellites() {
        System.out.println("=============================================");
        System.out.println("COSPARID           卫星名称  轨道半径  是否可用");
        for (int i = 0; i < satelliteList.size(); i++) {
            if (!satelliteList.get(i).isStatus()) {
                System.out.println(satelliteList.get(i).getCOSPARID() + "           " + satelliteList.get(i).getName() + "     "
                        + satelliteList.get(i).getOrbit() + "     " + satelliteList.get(i).isStatus());
            }
        }
        System.out.println("=============================================");
    }

    public void showSatelliteList(List<Satellite> satellites) {
        System.out.println("=============================================");
        System.out.println("COSPARID           卫星名称  轨道半径  是否可用");
        for (int i = 0; i < satellites.size(); i++) {
            System.out.println(satellites.get(i).getCOSPARID() + "           " + satellites.get(i).getName() + "     "
                    + satellites.get(i).getOrbit() + "     " + satellites.get(i).isStatus());
        }
        System.out.println("=============================================");
    }

    public boolean checkdistance(double orbit) {
        if (orbit < 1.2) {
            System.out.println("轨道半径不能小于1.2! ");
            return false;
        }
        if (satelliteList.isEmpty()) {
            return true;
        }
        //get the orbit of each satellite
        // compare with the input orbit, if the difference is less than 0.2 return false

        for (int i = 0; i < satelliteList.size(); i++) {
            if (Math.abs(satelliteList.get(i).getOrbitNum() - orbit) < 0.2) {
                System.out.println("轨道半径重复! ");
                return false;
            }
        }
        return true;
    }


}

class MainMenu {
    private final SatelliteManager satelliteManager;

    public MainMenu() {
        this.satelliteManager = new SatelliteManager();
    }

    // preload some satellites
    public void preloadSatellites() {
        Satellite satellite1 = new Satellite("COSPARID1", "卫星1", 1.2, true);
        Satellite satellite2 = new Satellite("COSPARID2", "卫星2", 1.4, true);
        Satellite satellite3 = new Satellite("COSPARID3", "卫星3", 1.6, true);
        Satellite satellite4 = new Satellite("COSPARID4", "卫星4", 1.8, true);
        Satellite satellite5 = new Satellite("COSPARID5", "卫星5", 2.0, true);
        satelliteManager.addSatellite(satellite1);
        satelliteManager.addSatellite(satellite2);
        satelliteManager.addSatellite(satellite3);
        satelliteManager.addSatellite(satellite4);
        satelliteManager.addSatellite(satellite5);
    }

    public void showMenu() {
        // Show menu options
        System.out.println("**********************************");
        System.out.println("     1-----显示当前活动卫星列表     ");
        System.out.println("     2-----注册新卫星              ");
        System.out.println("     3-----删除旧卫星              ");
        System.out.println("     4-----激活卫星                ");
        System.out.println("     5-----封锁（失活）卫星         ");
        System.out.println("     6-----显示失活卫星列表         ");
        System.out.println("     7-----按名称模糊查找卫星       ");
        System.out.println("     8-----修改卫星信息             ");
        System.out.println("     9-----退出!                   ");
        System.out.println("**********************************");

        //run: java -XX:+ShowCodeDetailsInExceptionMessages -cp bin App
        // Get user input
        System.out.print("选择: ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        try {
            handleUserInput(Integer.parseInt(option));
        } catch (NumberFormatException e) {
            System.out.println("输入无效! ");
        }
    }

    public void handleUserInput(int option) {
        // Handle user input based on the option
        switch (option) {
            case 1:
                // Show active satellites
                satelliteManager.showActiveSatellites();
                break;
            case 2:
                // Register a new satellite
                String COSPARID = getUserInput("COSPARID: ");
                String name = getUserInput("名  称: ");
                double orbit = getDoubleInput("轨道半径: ");
                if (orbit == -1 || !satelliteManager.checkdistance(orbit)) {
                    //return to option 2
                    handleUserInput(2);
                    break;
                }
                Satellite satellite = new Satellite(COSPARID, name, orbit, true);
                satelliteManager.addSatellite(satellite);
                System.out.print("注册下列新卫星成功!  ");
                // add 0
                DecimalFormat df = new DecimalFormat("#.00");
                System.out.println("satellite [COSPARID=" + COSPARID + ", name=" + name + ", r= " + df.format(orbit) + ", activated=true]");
                break;
            case 3:
                // Delete an old satellite
                String COSPARID3 = getUserInput("输入要删除的卫星的COSPARID: ");
                satelliteManager.deleteSatellite(COSPARID3);
                System.out.println("删除卫星成功! ");
                break;
            case 4:
                // Activate a satellite
                String COSPARID4 = getUserInput("输入要激活的卫星的COSPARID: ");
                satelliteManager.activateSatellite(COSPARID4);
                System.out.println("激活卫星成功! ");
                break;
            case 5:
                // Block a satellite
                String COSPARID5 = getUserInput("输入要封锁的卫星的COSPARID: ");
                satelliteManager.blockSatellite(COSPARID5);
                System.out.println("封锁卫星成功! ");
                break;
            case 6:
                // Show blocked satellites
                satelliteManager.showBlockedSatellites();
                break;
            case 7:
                // Search satellite by name
                String searchName= getUserInput("输入要查找的卫星的名称: ");
                System.out.println("查找结果: ");
                satelliteManager.searchSatelliteByName(searchName);
                //List<Satellite> searchResult = satelliteManager.searchSatelliteByName(searchName);
                //satelliteManager.showSatelliteList(searchResult);
                break;
            case 8:
                // Edit satellite information
                String COSPARID8 = getUserInput("输入要修改的卫星的COSPARID: ");
                Satellite satellite8 = satelliteManager.searchSatellite(COSPARID8);
                if (satellite8 == null) {
                    System.out.println("卫星不存在! ");
                    break;
                }
                System.out.println("当前卫星信息: satellite [COSPARID=" + satellite8.getCOSPARID() + ", name=" + satellite8.getName() + ", r= "
                        + satellite8.getOrbit() + ", activated=" + satellite8.isStatus() + "]");
                String newCOSPARID = getUserInput("新的COSPARID: ");
                String newName = getUserInput("新的名  称: ");
                double newOrbit = getDoubleInput("新的轨道半径: ");
                if (newOrbit == -1 || !satelliteManager.checkdistance(newOrbit)) {
                    //return to option 2
                    handleUserInput(2);
                    break;
                }
                Satellite newSatellite = new Satellite(newCOSPARID, newName, newOrbit, true);
                satelliteManager.editSatellite(COSPARID8, newSatellite);
                System.out.println("修改卫星信息成功! ");
                System.out.println("新的卫星信息: satellite [COSPARID=" + newCOSPARID + ", name=" + newName + ", r= " + newOrbit + ", activated=true]");
                break;
            case 9:
                // Exit the program
                exitProgram();
                break;
            default:
                System.out.println("输入无效! ");
                break;
        }
    }
    
    private String getUserInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private double getDoubleInput(String prompt) {
        String input = getUserInput(prompt);
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("轨道半径必须是数字! ");
            //return to option
            return -1;
        }
    }

    public void exitProgram() {
        // Exit the program
        System.out.println("正在关闭程序...");
        System.exit(0);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        MainMenu menu = new MainMenu();
        menu.preloadSatellites();

        while (true) {
            menu.showMenu();
        }
    }
}