/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface DAOinterface<T> {
    public int insert(T t);
    
    public int update(T t);
    
    public int delete(int t);
    
    public ArrayList<T> selectAll();
    
    public T selectById(int t);
}
