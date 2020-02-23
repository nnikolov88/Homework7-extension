package fmi.informatics.gui;

import javax.swing.table.AbstractTableModel;

import fmi.informatics.extending.Person;

public class FilterNameModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	Person[] people;

	
	public FilterNameModel(Person[] people) {
		this.people = people;
	}

	@Override
	public int getColumnCount() {
		return 2; 
	}

	@Override
	public int getRowCount() {
		return people.length; 
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:
				return people[rowIndex].getFirstName();
			case 1:
				return people[rowIndex].getNameCounter();

		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
			case 0:
				return "Първо Име";
			case 1:
				return "Брой имена ";
			default:
				return super.getColumnName(column);
		}
	}
}

