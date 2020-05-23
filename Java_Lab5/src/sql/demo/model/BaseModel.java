package sql.demo.model;

import java.util.Objects;


public class BaseModel {
    protected int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseModel baseModel = (BaseModel) o;
        return id == baseModel.id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
