/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.cs.inv;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdalla
 */
public class ItemDB {

    private List<Item> DB = new ArrayList<Item>(6);

    public ItemDB() {

        Item gpu1 = new Item("GPU1", "High Scale Gpu", "G", "NVIDIA GTX Quadro capable of pumping display to 4 monitors and is a great choice for multitasking", "&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;", "images/GPU1.png");
        Item gpu2 = new Item("GPU2", "Higher Scale Gpu", "G", "NVIDIA GTX Quadro 9988x capable of computing big graphical information and have a crushing g-computing power of 18 Ghz.", "&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;", "images/GPU2.png");
        Item gpu3 = new Item("GPU3", "Hologram Capable Gpu", "G", "This is the NVIDIA Tesla K40 graphics card developed by Lennnnovo Group.\n"
                + "\n"
                + "\n"
                + "It has 120 Gb of GDDR5X memory technology and it is capable of displaying a crystal clear hologram. The size of the hologram can scale as big as a movie theatre screen while maintaining the same display quality.\n"
                + "\n"
                + "\n"
                + "The K40 is extremely expensive and is only sold to big research corps. .\n"
                + "\n"
                + " ", "&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;", "images/GPU3.png");

        Item cpu1 = new Item("CPU1", "High Scale Cpu", "C", "Intel Xeon E7 is an entry level CPU. Most of the time it is purchased in paits for better synergy. Two fo these Cpu yields 64 core processors in total", "&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;", "images/CPU1.png");
        Item cpu2 = new Item("CPU2", "Higher Scale Cpu", "C", "Intel Xeon E7-8800 V2 is the big brother of teh Xeon E7 yielding 128 core processors, but it has higher energy consumption", "&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;", "images/CPU2.png");
        Item cpu3 = new Item("CPU3", "Cusotm Made Cpu", "C", "Using the Intel Xeon processors as bae material, we can create job specific processors offering out standing preformance and maybe an voer kill for the job as well. There isno beter way to prepare for teh future.", "&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;", "images/CPU3.png");

        DB.add(gpu1);
        DB.add(gpu2);
        DB.add(gpu3);
        DB.add(cpu1);
        DB.add(cpu2);
        DB.add(cpu3);

    }

    public <T> List<Item> getItems() {

        return DB;
    }

}
