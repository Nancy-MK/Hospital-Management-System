# 🏥 Hospital Management System – Java

A university assignment implementation for a Hospital Management System using Java, following an interface-based design pattern.

## 📌 Project Overview

This system simulates key functionalities in a hospital:
- Patient admission
- Treatment management
- Discharge process

## 🧱 Main Components

- `HospitalSystem`: Initializes hospital with patients, doctors, and wards
- `HospitalImplementer`: Core logic handling admission, treatment, discharge
- `Patient`, `Doctor`, `Ward`: Entities representing hospital elements
- `AdmissionRecord`, `TreatedRecord`, `DischargedRecord`: Lifecycle of a patient's hospital stay

## 🧪 Testing

Includes a `TestHospital` class to:
- Initialize with 1 patient, 2 doctors, 1 ward
- Admit the same patient with different doctors
- Compare treatments and discharge the best outcome

## 🛠 Technologies

- Java (Object-Oriented Design)
- JUnit for testing

---

## 📁 Folder Structure

- `src/`: Main source code
- `test/`: Unit testing
