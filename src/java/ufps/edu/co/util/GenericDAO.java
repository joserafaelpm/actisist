/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.util;

import java.util.List;

/**
 *
 * @author Familia Pena Mena
 */
public interface GenericDAO<T> {

	public List<T> list();
	public  <E> T find(E id);
	public void insert(T obj);
	public void update(T obj);
	public void delete(T obj);
}