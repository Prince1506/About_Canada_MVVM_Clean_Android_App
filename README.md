1. FOR RUNNING THE PROJECT
    Need to use JDK 9 for Robo electric unit test cases.

2. Unit test cases 100% test coverage for:-
  a. View Models
  b. Repositories
  C. Request and Response Pojo.

Note : I had use Robolectric library and this library has issue right now which
could be fixed by using JaCoco as Code Coverage inside android studio.

If you don't set it then one test will fail inside CanadaFactsViewModelTest only if
test coverage for all modules combinely if you just run test for all modules without
coverage then it will not fail. This issue is logged inside Robolectric library and
you can check it here
https://github.com/robolectric/robolectric/issues/3023

So if you want to test code coverage please check it separately. (One file at a time)

3. The Application uses navigator architecture in which we had created one laucnher activity and all the new screens
as per client will be navigated from this activity using navigator.