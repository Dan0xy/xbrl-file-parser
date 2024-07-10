# xbrl-file-parser

This project provides a Spring Boot application that uploads and parses XBRL (eXtensible Business Reporting Language) files to extract financial data and perform calculations. The extracted data and calculated values are returned in a JSON format.

## Features

•	Upload XBRL files using a REST API.
•	Parse ix:nonNumeric and ix:nonFraction elements from the XBRL file.
•	Perform financial calculations including Total Assets, Total Liabilities, Cash in Bank, Net Assets, and Debt Ratio.
•	Log all operations using SLF4J.

# Getting Started
## Prerequisites

•	Java 8 or higher
•	Maven
•	Spring Boot
•	SLF4J and Logback for logging

# Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/xbrl-file-parser.git
cd xbrl-file-parser
```
