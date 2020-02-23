package fmi.informatics.gui;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import fmi.informatics.extending.Person;
import fmi.informatics.util.FileReader;


public class MyFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	Person[] people = FileReader.readPeople();
	ArrayList<Person> peopleList = new ArrayList<Person>();	
	public MyFrame() {
		this.setSize(800, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(4,2));
		
		for (Person person : people) {
			peopleList.add(person);	
		}	

		JPanel buttonPanel = new JPanel();
		JPanel tablePanel = new JPanel();
		
		JButton sortNameButton = new JButton("Сортиране по Име (Възходящ ред)");
		JButton sortFamilyButton = new JButton("Сортиране по Фамилия (Низходящ ред)");
		JButton searchFamilyButton = new JButton("Търсене по Фамилия");
		JButton countNameButton = new JButton("Филтриране по Име");
		
		PersonDataModel personDataModel = new PersonDataModel(peopleList);

		JTable table = new JTable(personDataModel);		
		JScrollPane scroller = new JScrollPane(table);		
		this.add(tablePanel);
		this.add(buttonPanel);
		
		buttonPanel.setLayout(new GridLayout(4,2));
		;
		buttonPanel.add(sortNameButton);
		buttonPanel.add(sortFamilyButton);
		buttonPanel.add(searchFamilyButton);
		buttonPanel.add(countNameButton);

		scroller.setPreferredSize(new Dimension(500,300));
		tablePanel.add(scroller);
			
		countNameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				Person[] people = new Person[1];
				people[0] = new Person();
				String input = JOptionPane.showInputDialog("Въведете име, което искате да преброите:");
				 int counter = 0;
				 for(int i = 0;i<peopleList.size();i++) {
					 if(peopleList.get(i).getFirstName().toString().equals(input)) {
						counter++;					
					 }
				 }
				FilterNameModel filterNameModel = new FilterNameModel(people);

				 people[0].setFirstName(input);
				 people[0].setNameCounter(counter);

				table.setModel(filterNameModel);
				 table.repaint();			
			}
		});
		
		searchFamilyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				 String input = JOptionPane.showInputDialog("Въведете Фамилията на човека,който ще търсите:");
				 for(int i = 0;i<peopleList.size();i++) {
					 if(!(peopleList.get(i).getLastName().toString().equals(input))) {
						peopleList.remove(i); 
					 }
				 }
				 table.repaint();			
			}
		});
		
	
		
		sortNameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					
				Collections.sort(peopleList, new Comparator<Person>() {
				    @Override
				    public int compare(Person p1, Person p2) {
				        return p1.getFirstName().compareTo(p2.getFirstName());
				    }				    				    
				});							
				table.repaint();
			}
		});
		
		
		sortFamilyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					
				Collections.sort(peopleList, new Comparator<Person>() {
				    @Override
				    public int compare(Person p1, Person p2) {
				        return p2.getLastName().compareTo(p1.getLastName());
				    }				    				    
				});							
				table.repaint();
			}
		});
		
		
		this.setVisible(true);
	}
		
	
}
