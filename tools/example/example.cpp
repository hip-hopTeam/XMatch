// main.c
#include <stdlib.h>
#include <iostream>
#include "definition.h"
#include "jsonHandler.h"
#include "outputJson.h"
#include "matchAlgorithmTools.h"
#include "matchAlgorithm.h"
#include "parser.h"
using namespace std;

extern students student[310];
extern departments department[25];
extern addmitted addmitted_department[25];

int main() {
	jsonHandler jsonHandler_instance;
	if (jsonHandler_instance.processJSON("./input_data.txt")) {
		cout << "Parsing input_data.json successfully!" << endl;
	} else {
		cout << "Error: JSON file processing error!" << endl;
		return 1;
	}

	// key algorithm for Deputy
	matchAlgorithm handler;
	handler.algorithm();
	
	// output the results that stored in handler to JSON file
	outputJsonFile jsonOutputer;
	// cout << "handler.unlucky_department_number:" << handler.unlucky_department_number << endl;
	jsonOutputer.output("./output_data.txt", handler.unlucky_student_number, handler.unlucky_student, addmitted_department, handler.unlucky_department_number, handler.unlucky_department);

	return 0;
}