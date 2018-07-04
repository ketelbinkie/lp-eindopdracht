Besluiten
* Question catergory wordt alleen getoond in de enquete, verder wordt daar vooralsnog niets mee gedaan.

Know Errors
* BackEnd: EnqueteController -> enquete/all deze wordt gebruik in maintainEnquete.html en daar worden ook de enquete opgehaald die een record hebben in response. Deze mogen/kunnen niet meer gewijzigd worden. Dit geeft nu een 500 error. Oplossing een vergelijking maken met enquete en response en alleen de enquete teruggeven die niet in response voorkomen OPGELOST: a0cef1b8742bbd77a0abd944e68e066cb919d97e.
* BackEnd: Domein definitie van velden in Response, de id's naar andere entiteiten zijn gedefinieerd als String niet als int.

Wensen voor later
* maintainEnquete.html: vragen tonen die al bij de enquete zijn opgevoerd, zodat je duidelijk kan zien welke vragen je nog zou kunnen toevoegen.
* maintainQuestion.html: In plaats van logo, de beschikbare vragen tonen zodat je eerst kan zoeken of een vraag voorkomt en daar mee voorkomen dat er dubbele vragen ontstaan in de database.
* maintainTeamPeriod.html: op basis van seizoen en periode de start- en einddatum bepalen en voorinvullen (dit voorkomt start en einddata die niet passen bij het seizoen   )
* beschikbare vragen tonen op het scherm ‘Enquetesamenstellen’ per category. Dus eerst keuze voor category en dan de beschikbare vragen tonen.
* order - volgorde van vragen aangeven op het scherm ‘Enquetesamenstellen’
* logo en kleurstyle in de database, zodat makkelijk andere club het systeem kan gebruiken
* personen ophalen aan de hand van bijvoorbeeld een bondsnr, bijvoorbeeld door gebruik te maken van de KNVB api naar sportlink
* teamName.Html: controle op formaat team -> JO15-1
