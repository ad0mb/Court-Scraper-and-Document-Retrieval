# What is the Court Docket Scraper?
A solution for quickly and efficiently gathering a bulk amount of course case data and documents in an organized fashion. In its current state, it can only scrape Courtlink and retrieve records from the following counties:
- Harris County, Texas.
## How does it work?
Upon launching the Court Docket Scraper you will be greeted by an interactive interface where you can input your desired state and county, search terms, date ranges, attorney names, etc... Once started the program will first access and login using your credentials to the Courtlink website. After which it will input your chosen search terms and begin the search. It will surf through every case that appears under the search gathering case numbers and other relevant info. Once it is finished on the Courtlink site it will then go to the selected county site and begin to put in the previously acquired case numbers and retrieve whatever documents are available.
## Additional features:
- Stores all previously scraped and downloaded cases into a CSV file. This allows the ability to run closely related searches without ever having to worry about duplicates.
- Renames downloaded PDFs to the listed document preventing confusion on obscurely named PDF files.
- All retrieved files are organized into folders corresponding to their respective case numbers.
## Setup & Usage
### Download files
Download the Court Docket Scraper.jar along with the Courtlink Scraper files. Go to `C:\Users\{your username}\Desktop` and move the folder into your desktop (If Desktop does not exist create a folder named Desktop). This will be the folder where all your data, login information, and documents will be stored. As for the Court Docket Scraper.jar that can remain anywhere on your computer.
### Configure login's
Once you have placed the Courtlink Scraper file into Desktop, navigate to the Config folder inside of the Courtlink Scraper folder and open the JSON. Within the username and password corresponding to the websites name above, enter your username and password for the website.
### Install Firefox & Setup profile
To properly run this scraper you must download Firefox for Windows (**Not from Microsoft Store**). Then run press WinKey+R and enter `firefox.exe -p`. On this menu, you will see *Create Profile* on the left side. You will click on Create Profile and name it `BOT RUN`, the name must be identical or else the program will not know which Firefox profile to use. Once you have named this file properly click on it to open Firefox under that profile. 
#### Configure download path and settings
Within the "BOT RUN" profile go to settings and set the download path to `C:\Users\{your username}\Desktop\Courtlink Scraper\Temp`. In the same page, you should be able to find a box showing different file types a corresponding actions. For PDF set the option to *Save File" instead of *Open in Firefox*.
#### Add required extensions
Go to extensions and add Death By Captcha (DBC) to your Firefox add-ons. Create an account and log in making sure to check all the solve automatically boxes for each type of captcha you may encounter whilst the program navigates various websites.

***Only works on Windows operating systems.***
*As an alternative to using the premade .jar file you can also manually build the program executable by downloading the source code or running it from within an IDE (**Dependencies included in pom.xml**).*

## Future plans 
As of right now, I have plenty of features I want to implement down the line. Here is a list of the anticipated changes you should expect to see in the future:
- [ ] A more streamlined setup process (Automatic file setup, automatic Firefox profile configuration).
- [ ] Settings interface panel (login configurations, specific browser preferences, etc...).
- [ ] A larger variety of Counties to pull documents from.
- [ ] A separation of the Courtlink scraping process, and the county document retrieval process.
- [ ] Error panel on the interface.
- [ ] Integrating Google or another cloud service to store data beyond just locally (allowing multiple instances to run at once).

*These features are in no planned order as of right now, any questions or suggestions are welcome.*

## Contributing & Bug Reporting
### How can I contribute?
If you wish to contribute you should fork the repository and then clone it using `git clone https://github.com/your_username/Court-Docket-Scraper-and-Document-Retrieval.git`. No need to worry about dependencies, the Maven pom.xml is part of the source code so it will be cloned, all you have to do is input it into a Maven project. If changes to pom.xml are needed feel free to edit and make the necessary changes and dependency additions. Once your changes are complete and you wish to have them implemented you can create a pull request. Describe the changes you made and what purpose they serve.
### Bug reporting
If you encounter any issues or bugs feel free to come back to this repository and create a issue post in the issues tab on the top bar. Describe the bug and preferably where/what is specifically causing it, the added detail allows for an easier time troubleshooting and resolving the issue.