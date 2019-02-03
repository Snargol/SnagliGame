package model.genercis;

import java.util.ArrayList;

import model.enumeration.Direction;
import model.enumeration.OrderEnum;

public class Order {
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	ArrayList<OrderEnum> orders;
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public Order(ArrayList<OrderEnum> order) {
		this.orders = order;
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	public Direction orderToDirection(OrderEnum order) {
		switch (order) {
		case JUMP :
			return Direction.UP;
		case BIG_JUMP :
			return Direction.DOWN;
		case DOWN :
			return Direction.DOWN;
		case CLIMB_DOWN :
			return Direction.DOWN;
		case CLIMB_UP :
			return Direction.UP;
		default :
			return Direction.STATIC;
		}
	}

	public void addOrder(OrderEnum order) {
		this.orders.add(order);
	}
	
	public void removeOrder(OrderEnum order) {
		int i = orders.indexOf(order);
		orders.remove(i);
	}
	
	public boolean containOrder(OrderEnum order) {
		boolean containOrder = (orders.contains(order))? true : false;
		return containOrder;
	}
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/





}
