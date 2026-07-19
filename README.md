# Hospital Management System (Java)

![Java](https://img.shields.io/badge/Java-OOP-orange) ![Build](https://img.shields.io/badge/build-Maven-blue) ![Tests](https://img.shields.io/badge/testing-JUnit-green)

A university coursework implementation of a Hospital Management System in Java, built around an **interface-based design** so that the core admission/treatment/discharge workflow can be extended or swapped out without touching client code.

## Project overview

The system models the lifecycle of a patient moving through a hospital:

1. **Admission** - a patient is admitted to a ward under a doctor
2. **Treatment** - a treatment plan and medical history are attached to the admission
3. **Discharge** - the outcome is recorded and billing is generated

## Main components

| Class | Responsibility |
|---|---|
| `HospitalSystem` | Bootstraps the hospital with patients, doctors, and wards |
| `HospitalImplementer` | Implements `HMSInterface`; core logic for admission, treatment, and discharge |
| `HMSInterface` | Defines the contract the hospital's core workflow must satisfy |
| `Patient`, `Doctor`, `Ward`, `Bed`, `Specialization`, `Schedule`, `Equipment` | Core domain entities |
| `AdmissionRecord`, `TreatedRecord`, `DischargedRecord`, `DischargeSummary` | Track a patient's stay from admission through discharge |
| `TreatmentPlan`, `MedicalHistory`, `Medication` | Clinical detail attached to a patient's record |
| `BillingDetails`, `InsuranceDetails`, `ContactDetails` | Administrative and billing information |
| `TestHospital` | Test harness exercising the workflow end-to-end |

## Design approach

The system is built against the `HMSInterface` contract rather than a concrete implementation, so `HospitalImplementer` can be replaced with an alternative implementation (e.g. a database-backed one) without changing any calling code. This interface-first approach is standard practice for keeping business logic testable and decoupled from any one data source.

## Testing

`TestHospital` exercises the core workflow:

- Initialises the system with 1 patient, 2 doctors, and 1 ward
- Admits the same patient under different doctors
- Compares treatment outcomes and discharges the patient with the better outcome

## Tech stack

Java (interface-driven OOP), JUnit, Maven

## Getting started

```bash
git clone https://github.com/Nancy-MK/Hospital-Management-System.git
cd Hospital-Management-System
mvn compile
mvn test
```

## Licence

Developed for academic purposes as part of the BSc Computer Science programme. All rights reserved (c) Nancy Kamal.
