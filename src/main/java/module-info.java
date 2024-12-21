module com.patrick {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires org.yaml.snakeyaml;
    requires javafx.base;


    opens com.patrick to javafx.fxml,hibernate.entitymanager,
    java.persistence,
    org.hibernate.orm.core, 
    org.yaml.snakeyaml,
    java.sql,
    lombok;
opens com.patrick.entities to org.hibernate.orm.core, java.sql, javafx.base;        
opens com.patrick.controllers to javafx.fxml;
opens com.patrick.repositories.bd to org.hibernate.orm.core, hibernate.entitymanager, java.sql;

    exports com.patrick;
}
