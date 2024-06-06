package ch.fhnw.pizza.data.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;   
    
    //@Column(name = "finalprice")
    //private Double finalprice;

    //onetoone mapping has to be done  between order and payment
    //it is unidirectional for sure 
    //but ask where mapped by should be done
    //-----------------------------------
    //@OneToOne(mappedBy = "payment")
    //or is it using the join
    //private Order order;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Double getFinalprice() {
        //return order.getFinalprice();
        return order != null ? order.getFinalprice() : null;
    }
    //-----------------------------------
    @Column(name = "completed")
    private Boolean completed;


    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

   

   // public Double getFinalprice() {
   //     return finalprice;
   // }

   // public void setFinalprice(Double finalprice) {
   //     this.finalprice = finalprice;
    //}
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
