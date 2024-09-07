/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author phamthithuphuong
 */
public interface ChiTietInterface<T> {
    public int insert(ArrayList<T> t);
    public int delete(int t);
    public int update(ArrayList<T> t, int pk);
    public ArrayList<T> selectAll(int t);
}
