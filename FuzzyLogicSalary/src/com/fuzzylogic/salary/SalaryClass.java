package com.fuzzylogic.salary;

import java.util.Scanner;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class SalaryClass 
{
	public static void main(String[] args) throws Exception 
	{
		// Otwieranie pliku 'FCL'
		String filename = "salary.fcl";
		FIS fis = FIS.load(filename, true);

		Scanner scan = new Scanner(System.in);
		// B��d podczas otwierania pliku
		if (fis == null) 
		{
			System.err.println("Can't load file: '" + filename + "'");
			System.exit(1);
		}

		// Get default function block
		FunctionBlock fb = fis.getFunctionBlock(null);

		// Set inputs
		System.out.println("Czy chcesz poda� warto�ci do oblicze� samodzielnie, czy u�y� przygotowanego przyk�adu? Wpisz 1 dla przyk�adu lub 2 je�li chcesz sam poda� warto�ci");
		int value = scan.nextInt();
		if(value == 1)
		{
			fb.setVariable("experience", 1.0);
			fb.setVariable("skills", 1.0);
			fb.setVariable("technology", 4.5);
		}
		else
		{
			try
			{
				System.out.println("Podaj do�wiadczenie w latach (double)");
				double experience = scan.nextDouble();
				System.out.println("Podaj umiej�tno�ci w skali 0-10 (double)");
				double skills = scan.nextDouble();
				System.out.println("Podaj liczb� znanych technologii w skali 0-10 (double)");
				double technology = scan.nextDouble();
				fb.setVariable("experience", experience);
				fb.setVariable("skills", skills);
				fb.setVariable("technology", technology);
				scan.close();
			}
			catch(Exception e)
			{
				System.out.println("Podane liczby musz� zawiera� przecinek np. 4,0 ; 2,5");
			}
		}
		

		// Evaluate
		fb.evaluate();

		// Show output variable's chart
		fb.getVariable("salary").defuzzify();

		// Print ruleSet
		System.out.println(fb);
		System.out.println("Salary: " + fb.getVariable("salary").getValue() + "z� 0/100 gr");
	}
}
