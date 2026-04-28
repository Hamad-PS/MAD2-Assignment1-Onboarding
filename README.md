  # Mobile App Development 2 — Assignment 1
  ## Onboarding Screens

  **Student:** Hamad Maher Al Rafati
  
  **Student ID:** 1320220704
  
  **Language:** Java
  
  **Min SDK:** 33  |  Target SDK:** 35

  ## Overview
  A three-step onboarding flow demonstrating Fragment-to-Activity
  communication via interfaces, data passing via Bundle + setArguments,
  and screen navigation via FragmentManager.replace with back stack.

  ## Screens
  1. **Fragment1** — Collect user name (with validation).
  2. **Fragment2** — Collect email, gender, age, university.
  3. **Fragment3** — Confirmation checkbox, dynamic Finish button.

  ## Architecture
  - `MainActivity` hosts all fragments and implements three interfaces:
    - `Fragment1.OnNameSentListener`
    - `Fragment2.OnUserDetailsSentListener`
    - `Fragment3.OnConfirmationChangedListener`
  - Each fragment defines its own listener and casts the host in `onAttach`.
  - All data passes through `Bundle` under the key `"user_name"`.
  - Screen transitions use `replace().addToBackStack(null).commit()`.
  - CheckBox state handled with `setOnCheckedChangeListener`.
