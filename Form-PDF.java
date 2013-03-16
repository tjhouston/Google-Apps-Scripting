// TJ Houston tjhouston.com (tj@tjhouston.com)
// Bus Conduct Report
// Get template from Google Docs and name it
var docTemplate = "1sbsvGkJCmkN-NDkKLpZcZUy8h9x2YHkVmYOg9jJwYrI"; // *** replace with your template ID ***
var docName = "BusConductReport";

// When Form Gets submitted
function onFormSubmit(e) {
//Get information from form and set as variables
var email_address = "test@huron-city.k12.oh.us, thouston@huron-city.k12.oh.us";
var full_name = e.values[2];
var grade_level = e.values[3];
var incident_date = e.values[4];
var bus_number = e.values[5];
var violations = e.values[9];
var explanation = e.values[10];
var drivers_coa = e.values[11];

// Get document template, copy it as a new temp doc, and save the Doc’s id
var copyId = DocsList.getFileById(docTemplate)
.makeCopy(docName+' for '+full_name)
.getId();
// Open the temporary document
var copyDoc = DocumentApp.openById(copyId);
// Get the document’s body section
var copyBody = copyDoc.getActiveSection();

// Replace place holder keys,in our google doc template
copyBody.replaceText('keyFullName', full_name);
copyBody.replaceText('keyTodaysDate', incident_date);
copyBody.replaceText('keyGradeLevel', grade_level);
copyBody.replaceText('keyBusNumber', bus_number);
copyBody.replaceText('keyViolations', violations);
copyBody.replaceText('keyExplanation',explanation);
copyBody.replaceText('keyDriverCOA', drivers_coa);

// Save and close the temporary document
copyDoc.saveAndClose();

// Convert temporary document to PDF
var pdf = DocsList.getFileById(copyId).getAs("application/pdf");

// Attach PDF and send the email
var subject = "Bus Conduct Report";
var body = "Here is the Bus Conduct form for " + full_name + "";
MailApp.sendEmail(email_address, subject, body, {htmlBody: body, attachments: pdf});

// Delete temp file
DocsList.getFileById(copyId).setTrashed(true);
}