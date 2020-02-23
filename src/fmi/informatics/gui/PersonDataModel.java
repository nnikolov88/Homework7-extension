package fmi.informatics.gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import fmi.informatics.extending.Person;

public class PersonDataModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	ArrayList<Person> peopleList = new ArrayList<Person>();	


	public PersonDataModel(ArrayList<Person> peopleList) {
		this.peopleList = peopleList;
	}

	@Override
	public int getColumnCount() {
		return 3; 
	}

	@Override
	public int getRowCount() {
		return peopleList.size(); 
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:
				return peopleList.get(rowIndex).getFirstName();
			case 1:
				return peopleList.get(rowIndex).getMiddleName();
			case 2:
				return peopleList.get(rowIndex).getLastName();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
			case 0:
				return "Име";
			case 1:
				return "Презиме";
			case 2:
				return "Фамилия";	
			default:
				return super.getColumnName(column);
		}
	}
}