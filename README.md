# xbrl-file-parser

This project provides a Spring Boot application that uploads and parses HTML files containing XBRL (eXtensible Business Reporting Language) data to extract financial information . The extracted data  are returned in a JSON format.
The data used for parsing and calculations can be obtained from the Companies House monthly accounts data(https://download.companieshouse.gov.uk/en_monthlyaccountsdata.html). The files downloaded from this website can be uploaded to the application for parsing and analysis.

## Features

•	Upload HTML files containing XBRL data using a REST API.
•	Parse ix:nonNumeric and ix:nonFraction elements from the XBRL file.
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

2.	Build the project using Maven:

```bash
mvn clean install
```
3.	Run the Spring Boot application:

```bash
mvn spring-boot:run
```

### Usage

1.	Upload an HTML file containing XBRL data:
You can use a tool like curl or Postman to upload an HTML file to the application.

```bash
curl -F "file=@/path/to/your/file.html" http://localhost:8080/upload
```
2.	Response:
The API will return a JSON response containing the extracted elements .

## Example

### Request:

```bash
curl -F "file=@/path/to/your/file.html" http://localhost:8080/upload
```

### Response:
```bash
{
    "core:ProvisionsForLiabilitiesBalanceSheetSubtotal": "37",
    "bus:NameProductionSoftware": "CCH Software",
    "TotalAssets": 9508.0,
    "bus:EntityTradingStatus": "",
    "direp:StatementThatMembersHaveNotRequiredCompanyToObtainAnAudit": "T",
    "core:DateAuthorisationFinancialStatementsForIssue": "27 June 2024",
    "bus:ApplicableLegislation": "",
    "core:TaxPolicy": "The tax expense represents the sum of the tax currently payable and deferred tax.",
    "bus:ReportAnAmendedRevisedVersionPreviouslyFiledReportTruefalse": "false",
    "bus:UKCompaniesHouseRegisteredNumber": "SC773202",
    "core:ImpairmentNon-financialAssetsPolicy": "At each reporting ",
    "core:DirectorSigningFinancialStatements": "",
    "NetAssets": -1151.0,
    "bus:AccountsStatusAuditedOrUnaudited": "",
    "core:OtherCreditors": "10,659",
    "direp:StatementThatDirectorsAcknowledgeTheirResponsibilitiesUnderCompaniesAct": "The director acknowledges her responsibilities for complying with the requirements of the Companies Act 2006 with respect to accounting records and the preparation of financial statements.",
    "bus:NameEntityOfficer": "Ms L Hill",
    "CashInBank": 7869.0,
    "core:AccumulatedDepreciationImpairmentPropertyPlantEquipment": "439",
    "core:NetAssetsLiabilities": "128",
    "direp:StatementThatAccountsHaveBeenPreparedInAccordanceWithProvisionsSmallCompaniesRegime": "These financial statements have been prepared and delivered in accordance with the provisions applicable to companies subject to the small companies regime.",
    "core:Creditors": "10,659",
    "bus:StartDateForPeriodCoveredByReport": "2023-06-20",
    "bus:ScopeAccounts": "",
    "core:GeneralDescriptionBasisMeasurementUsedInPreparingFinancialStatements": "The financial statements have been prepared under the historical cost convention and in accordance with the principal accounting policies set out below.",
    "core:Debtors": "1,639",
    "bus:ReportIncludesDetailedProfitLossStatementTruefalse": "false",
    "core:DeferredTaxPolicy": "Deferred tax liabilities are generally recognised for all timing differences and deferred tax assets are recognised to the extent that it is probable that they will be recovered against the reversal of deferred tax liabilities or other future taxable profits. Such assets and liabilities are not recognised if the timing difference arises from goodwill or from the initial recognition of other assets and liabilities in a transaction that affects neither the tax profit nor the accounting profit.",
    "bus:LegalFormEntity": "",
    "core:IncreaseFromDepreciationChargeForYearPropertyPlantEquipment": "439",
    "bus:CountryFormationOrIncorporation": "",
    "core:CashCashEquivalentsPolicy": "Cash and cash equivalents",
    "bus:AccountingStandardsApplied": "",
    "core:CurrentAssets": "9,508",
    "core:StatementComplianceWithApplicableReportingFramework": "These financial statements have been prepared in accordance with FRS 102 “The Financial Reporting Standard applicable in the UK and Republic of Ireland” (“FRS 102”) and the requirements of the Companies Act 2006 as applicable to companies subject to the small companies regime. The disclosure requirements of section 1A of FRS 102 have been applied other than where additional disclosure is required to show a true and fair view.",
    "core:EmployeeBenefitsPolicy": "The costs of short-term employee benefits are recognised as a liability and an expense, unless those costs are required to be recognised as part of the cost of stock or ",
    "core:TotalAssetsLessCurrentLiabilities": "165",
    "direp:EntityHasTakenExemptionUnderCompaniesActInNotPublishingItsOwnProfitLossAccountTruefalse": "The director of the company has elected not to include a copy of the profit and loss account within the financial statements.true",
    "core:RevenueRecognitionPolicy": "Turnover is recognised at the fair value of the consideration received or receivable for goods and services provided in the normal course of business",
    "core:LessorOperatingLeasePolicy": "Rentals payable under operating leases, ",
    "bus:AccountsType": "",
    "direp:StatementThatCompanyEntitledToExemptionFromAuditUnderSection477CompaniesAct2006RelatingToSmallCompanies": "For the financial period ended 31 March 2024 the company was entitled to exemption from audit under section 477 of the Companies Act 2006",
    "core:Equity": "128",
    "core:AverageNumberEmployeesDuringPeriod": "1",
    "DebtRatio": 112.10559528817838,
    "TotalLiabilities": 10659.0,
    "core:FinancialInstrumentsClassificationPolicy": "The company has elected to apply the provisions of Section 11 ‘Basic Financial Instruments’ and Section 12 ‘Other Financial Instruments Issues’ of FRS 102 to all of its financial instruments. ",
    "bus:DescriptionPrincipalActivities": "No description of principal activity",
    "core:TotalAdditionsIncludingFromBusinessCombinationsPropertyPlantEquipment": "1,755",
    "bus:BalanceSheetDate": "31 March 2024",
    "bus:ReportTitle": "UNAUDITED FINANCIAL STATEMENTS",
    "core:PropertyPlantEquipment": "1,316",
    "core:OtherDebtors": "202",
    "bus:EntityDormantTruefalse": "false",
    "core:CurrentIncomeTaxPolicy": "The tax currently payable is based on taxable profit for the year. Taxable profit differs from net profit as reported in the ",
    "core:NetCurrentAssetsLiabilities": "-1,151",
    "core:DescriptionDepreciationMethodForPropertyPlantEquipment": "25% Straight Line",
    "bus:EndDateForPeriodCoveredByReport": "2024-03-31",
    "core:CashBankOnHand": "7,869",
    "bus:EntityCurrentLegalOrRegisteredName": "LYNN HILL GARDEN DESIGN LTD",
    "core:PropertyPlantEquipmentGrossCost": "1,755",
    "core:DescriptionMeasurementBasisForPropertyPlantEquipment": "Tangible fixed assets ",
    "core:TradeDebtorsTradeReceivables": "1,437",
    "bus:VersionProductionSoftware": "CCH Accounts Production 2024.100",
    "aurep:DateAuditorsReport": "27 June 2024"
}
```
