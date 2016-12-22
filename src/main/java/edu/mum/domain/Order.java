package edu.mum.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Order implements Serializable {
	@Id 
 	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ORDER_ID")
	private Long id = null;
	private int version = 0;

	private String orderNumber;

	@OneToMany(targetEntity=OrderItem.class, mappedBy="order", fetch=FetchType.EAGER) 
	private List<OrderItem> items = new ArrayList<OrderItem>();

	//private OrderPayment payment = new OrderPayment();

	//@ManyToOne(fetch = FetchType.EAGER)
	//@JoinColumn(name = "member_id")
	//private User member; // / customer info

	//private int orderType; //store pickup or online order
	
	private int orderStatus;
	
	/*public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}*/


	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Order() {
	}

	public Order(String orderNumber, List<OrderItem> items) {
		this.orderNumber = orderNumber;
		this.items = items;
		this.orderStatus = 1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}


	public void addOrderItem(OrderItem orderItem) {
		this.items.add(orderItem);
		orderItem.setOrder(this);
	}

}
