package lab5;

import lab5.data.Corvette;
import lab5.data.Fleet;
import lab5.data.Sailboat;
import lab5.data.Steamboat;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    private static Fleet data;


    public MyTableModel(Fleet fleet){
        this.data = fleet;
    }

    @Override
    public int getRowCount() {
        return data.getCount();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return data.getShip(rowIndex).getName();
            case 1:
                return data.getShip(rowIndex).getType();
            case 2:
                return data.getShip(rowIndex).getSpeed();
            case 3:
                return data.getShip(rowIndex).getCost();
            case 4:
                return data.getShip(rowIndex).getShipFeatures();
            default:
                return "default";
        }
    }

    @Override
    public String getColumnName(int column){
        switch (column){
            case 0: return "Название";
            case 1: return "Тип";
            case 2: return "Скорость";
            case 3: return "Стоимость";
            case 4: return "Особенности";
            default: return "Что пошло не так";
        }
    }

    public void delete(int index){
        this.data.sellShip(index);
        fireTableDataChanged();
    }


    public void add(Corvette corvette){
        data.add(corvette);
        data.setBudget(data.getBudget()-500);
        fireTableDataChanged();
    }
    public void add(Steamboat steamboat){
        data.add(steamboat);
        data.setBudget(data.getBudget()-400);
        fireTableDataChanged();
    }
    public void add(Sailboat sailboat){
        data.add(sailboat);
        data.setBudget(data.getBudget()-300);
        fireTableDataChanged();
    }

    public int getBalance(){
        return data.getBudget();
    }

    public void setBalance(int budget){
        data.setBudget(budget);
    }

}
