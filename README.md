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

4. Images are coming of improper size from server. To display them completely I am making them center fit.
Henceforth if image is coming of proper width it'll be shown as rectangle but if it's not coming of proper width then
it will be shown as it's.

The second option was to force it to be shown as rectangle doing so will need to crop the images maintaining
the aspect ratio henceforth image detail will be lost. Hence I had not used this option.

