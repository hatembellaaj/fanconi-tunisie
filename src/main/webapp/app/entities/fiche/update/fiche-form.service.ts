import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IFiche, NewFiche } from '../fiche.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFiche for edit and NewFicheFormGroupInput for create.
 */
type FicheFormGroupInput = IFiche | PartialWithRequiredKeyOf<NewFiche>;

type FicheFormDefaults = Pick<NewFiche, 'id'>;

type FicheFormGroupContent = {
  id: FormControl<IFiche['id'] | NewFiche['id']>;
  nDossier: FormControl<IFiche['nDossier']>;
  dateDiagnostic: FormControl<IFiche['dateDiagnostic']>;
  dateEnregistrement: FormControl<IFiche['dateEnregistrement']>;
  medecin: FormControl<IFiche['medecin']>;
  hopital: FormControl<IFiche['hopital']>;
  service: FormControl<IFiche['service']>;
  degConsang: FormControl<IFiche['degConsang']>;
  placeEnfant: FormControl<IFiche['placeEnfant']>;
  nbVivant: FormControl<IFiche['nbVivant']>;
  nbMort: FormControl<IFiche['nbMort']>;
  mortNe: FormControl<IFiche['mortNe']>;
  avortement: FormControl<IFiche['avortement']>;
  cousin: FormControl<IFiche['cousin']>;
  membre: FormControl<IFiche['membre']>;
  arbregenealogique: FormControl<IFiche['arbregenealogique']>;
  arbregenealogiqueContentType: FormControl<IFiche['arbregenealogiqueContentType']>;
  arbregenealogiquecancer: FormControl<IFiche['arbregenealogiquecancer']>;
  arbregenealogiquecancerContentType: FormControl<IFiche['arbregenealogiquecancerContentType']>;
  syndromeAnemique: FormControl<IFiche['syndromeAnemique']>;
  syndromeHem: FormControl<IFiche['syndromeHem']>;
  syndromeInf: FormControl<IFiche['syndromeInf']>;
  decouverteFort: FormControl<IFiche['decouverteFort']>;
  enqueteFam: FormControl<IFiche['enqueteFam']>;
  typeCancer: FormControl<IFiche['typeCancer']>;
  cancer: FormControl<IFiche['cancer']>;
  tailleNaiss: FormControl<IFiche['tailleNaiss']>;
  poidsNaiss: FormControl<IFiche['poidsNaiss']>;
  hypotrophie: FormControl<IFiche['hypotrophie']>;
  troubleCroi: FormControl<IFiche['troubleCroi']>;
  aAgeChDiag: FormControl<IFiche['aAgeChDiag']>;
  mAgeChDiag: FormControl<IFiche['mAgeChDiag']>;
  aAgeOssDiag: FormControl<IFiche['aAgeOssDiag']>;
  mAgeOssDiag: FormControl<IFiche['mAgeOssDiag']>;
  ageRetard: FormControl<IFiche['ageRetard']>;
  poids: FormControl<IFiche['poids']>;
  poidsDS: FormControl<IFiche['poidsDS']>;
  taille: FormControl<IFiche['taille']>;
  tailleDS: FormControl<IFiche['tailleDS']>;
  atteinteCut: FormControl<IFiche['atteinteCut']>;
  tacheCaf: FormControl<IFiche['tacheCaf']>;
  ventre: FormControl<IFiche['ventre']>;
  membreSup: FormControl<IFiche['membreSup']>;
  membreInf: FormControl<IFiche['membreInf']>;
  visage: FormControl<IFiche['visage']>;
  thorax: FormControl<IFiche['thorax']>;
  dOS: FormControl<IFiche['dOS']>;
  hyperPig: FormControl<IFiche['hyperPig']>;
  hypochromique: FormControl<IFiche['hypochromique']>;
  couleurPeau: FormControl<IFiche['couleurPeau']>;
  autreAtCut: FormControl<IFiche['autreAtCut']>;
  atteinteTete: FormControl<IFiche['atteinteTete']>;
  microcephalie: FormControl<IFiche['microcephalie']>;
  microphtalmie: FormControl<IFiche['microphtalmie']>;
  facieTrig: FormControl<IFiche['facieTrig']>;
  traitsFin: FormControl<IFiche['traitsFin']>;
  autreAtTete: FormControl<IFiche['autreAtTete']>;
  empreintedigitiforme: FormControl<IFiche['empreintedigitiforme']>;
  malUro: FormControl<IFiche['malUro']>;
  uIV: FormControl<IFiche['uIV']>;
  echo: FormControl<IFiche['echo']>;
  reinEctop: FormControl<IFiche['reinEctop']>;
  siegeEctopie: FormControl<IFiche['siegeEctopie']>;
  reinFerChev: FormControl<IFiche['reinFerChev']>;
  petitRein: FormControl<IFiche['petitRein']>;
  reinUnique: FormControl<IFiche['reinUnique']>;
  ectopTest: FormControl<IFiche['ectopTest']>;
  vergeInsuf: FormControl<IFiche['vergeInsuf']>;
  autreAnomVerge: FormControl<IFiche['autreAnomVerge']>;
  retardPubertaire: FormControl<IFiche['retardPubertaire']>;
  mTanner: FormControl<IFiche['mTanner']>;
  pTanner: FormControl<IFiche['pTanner']>;
  tTanner: FormControl<IFiche['tTanner']>;
  anomUrin: FormControl<IFiche['anomUrin']>;
  typeAnomUrin: FormControl<IFiche['typeAnomUrin']>;
  atteinteOss: FormControl<IFiche['atteinteOss']>;
  radiosfaites: FormControl<IFiche['radiosfaites']>;
  anomPouce: FormControl<IFiche['anomPouce']>;
  surnumerarie: FormControl<IFiche['surnumerarie']>;
  agenesiePouce: FormControl<IFiche['agenesiePouce']>;
  bifide: FormControl<IFiche['bifide']>;
  hypoPouce: FormControl<IFiche['hypoPouce']>;
  aspectPouce: FormControl<IFiche['aspectPouce']>;
  hypoEminence: FormControl<IFiche['hypoEminence']>;
  absenceRadial: FormControl<IFiche['absenceRadial']>;
  pouceBas: FormControl<IFiche['pouceBas']>;
  autreAnomPouce: FormControl<IFiche['autreAnomPouce']>;
  anomAutDoigts: FormControl<IFiche['anomAutDoigts']>;
  courtstrapus: FormControl<IFiche['courtstrapus']>;
  syndactylie: FormControl<IFiche['syndactylie']>;
  autreAnomDoigts: FormControl<IFiche['autreAnomDoigts']>;
  anomalieos: FormControl<IFiche['anomalieos']>;
  agenesieRadius: FormControl<IFiche['agenesieRadius']>;
  autreanomalieMembresup: FormControl<IFiche['autreanomalieMembresup']>;
  anomOrteil: FormControl<IFiche['anomOrteil']>;
  preciseAnomOrt: FormControl<IFiche['preciseAnomOrt']>;
  lCH: FormControl<IFiche['lCH']>;
  autreanomalieMembreinf: FormControl<IFiche['autreanomalieMembreinf']>;
  anomRachis: FormControl<IFiche['anomRachis']>;
  preciseAnomRac: FormControl<IFiche['preciseAnomRac']>;
  autreAnomOss: FormControl<IFiche['autreAnomOss']>;
  anomNeuro: FormControl<IFiche['anomNeuro']>;
  retardMent: FormControl<IFiche['retardMent']>;
  hypoacousie: FormControl<IFiche['hypoacousie']>;
  strabisme: FormControl<IFiche['strabisme']>;
  performanceScolaire: FormControl<IFiche['performanceScolaire']>;
  autreanomalieneurologique: FormControl<IFiche['autreanomalieneurologique']>;
  anomCardVas: FormControl<IFiche['anomCardVas']>;
  echoCoeur: FormControl<IFiche['echoCoeur']>;
  preciseAnomCardio: FormControl<IFiche['preciseAnomCardio']>;
  anomDig: FormControl<IFiche['anomDig']>;
  preciseAnomDig: FormControl<IFiche['preciseAnomDig']>;
  endocrinopathie: FormControl<IFiche['endocrinopathie']>;
  diabete: FormControl<IFiche['diabete']>;
  insulinoDep: FormControl<IFiche['insulinoDep']>;
  hypothyroidie: FormControl<IFiche['hypothyroidie']>;
  ageDecouverte: FormControl<IFiche['ageDecouverte']>;
  autreEndocrinopathie: FormControl<IFiche['autreEndocrinopathie']>;
  autreAnomCong: FormControl<IFiche['autreAnomCong']>;
  dateNumSanguine: FormControl<IFiche['dateNumSanguine']>;
  age: FormControl<IFiche['age']>;
  hb: FormControl<IFiche['hb']>;
  vGM: FormControl<IFiche['vGM']>;
  retic: FormControl<IFiche['retic']>;
  leuco: FormControl<IFiche['leuco']>;
  pNN: FormControl<IFiche['pNN']>;
  plq: FormControl<IFiche['plq']>;
  hbInf: FormControl<IFiche['hbInf']>;
  plqInf: FormControl<IFiche['plqInf']>;
  pNNInf: FormControl<IFiche['pNNInf']>;
  electrophoreseHb: FormControl<IFiche['electrophoreseHb']>;
  hbf: FormControl<IFiche['hbf']>;
  myelogramme: FormControl<IFiche['myelogramme']>;
  cellularite: FormControl<IFiche['cellularite']>;
  erythroblaste: FormControl<IFiche['erythroblaste']>;
  morphologieEryth: FormControl<IFiche['morphologieEryth']>;
  morphologieGran: FormControl<IFiche['morphologieGran']>;
  morphologieMega: FormControl<IFiche['morphologieMega']>;
  granuleux: FormControl<IFiche['granuleux']>;
  dysmyelopoiese: FormControl<IFiche['dysmyelopoiese']>;
  megacaryocytes: FormControl<IFiche['megacaryocytes']>;
  blaste: FormControl<IFiche['blaste']>;
  excesblastes: FormControl<IFiche['excesblastes']>;
  bOM: FormControl<IFiche['bOM']>;
  adipocytes: FormControl<IFiche['adipocytes']>;
  ubiquitination: FormControl<IFiche['ubiquitination']>;
  resUbiquitination: FormControl<IFiche['resUbiquitination']>;
  groupComp: FormControl<IFiche['groupComp']>;
  mutationFANC: FormControl<IFiche['mutationFANC']>;
  congelationCellule: FormControl<IFiche['congelationCellule']>;
  labo: FormControl<IFiche['labo']>;
  typePrelevement: FormControl<IFiche['typePrelevement']>;
  scoreClinique: FormControl<IFiche['scoreClinique']>;
  scoreEBMT: FormControl<IFiche['scoreEBMT']>;
  score: FormControl<IFiche['score']>;
  transfusion: FormControl<IFiche['transfusion']>;
  ageTransf: FormControl<IFiche['ageTransf']>;
  delaiDiag: FormControl<IFiche['delaiDiag']>;
  nbCG: FormControl<IFiche['nbCG']>;
  nbCP: FormControl<IFiche['nbCP']>;
  chelationFer: FormControl<IFiche['chelationFer']>;
  chelateur: FormControl<IFiche['chelateur']>;
  nilevar: FormControl<IFiche['nilevar']>;
  danatrol: FormControl<IFiche['danatrol']>;
  oxynethadone: FormControl<IFiche['oxynethadone']>;
  androtordyl: FormControl<IFiche['androtordyl']>;
  autreAndrogene: FormControl<IFiche['autreAndrogene']>;
  androDebut: FormControl<IFiche['androDebut']>;
  androFin: FormControl<IFiche['androFin']>;
  observance: FormControl<IFiche['observance']>;
  toxicite: FormControl<IFiche['toxicite']>;
  autreToxicite: FormControl<IFiche['autreToxicite']>;
  enqueteHLA: FormControl<IFiche['enqueteHLA']>;
  pourquoiEnqHLA: FormControl<IFiche['pourquoiEnqHLA']>;
  nbFratTyp: FormControl<IFiche['nbFratTyp']>;
  nbFratNTyp: FormControl<IFiche['nbFratNTyp']>;
  donneurComp: FormControl<IFiche['donneurComp']>;
  preciseDonneur: FormControl<IFiche['preciseDonneur']>;
  donneur: FormControl<IFiche['donneur']>;
  greffeFaite: FormControl<IFiche['greffeFaite']>;
  delaiRappDiag: FormControl<IFiche['delaiRappDiag']>;
  pourquoiNfaite: FormControl<IFiche['pourquoiNfaite']>;
  cyclophosphamide: FormControl<IFiche['cyclophosphamide']>;
  fludarabine: FormControl<IFiche['fludarabine']>;
  busulfan: FormControl<IFiche['busulfan']>;
  doseCyclo: FormControl<IFiche['doseCyclo']>;
  doseFlu: FormControl<IFiche['doseFlu']>;
  doseBus: FormControl<IFiche['doseBus']>;
  autreConditionnement: FormControl<IFiche['autreConditionnement']>;
  irradiation: FormControl<IFiche['irradiation']>;
  doseTotaleIrr: FormControl<IFiche['doseTotaleIrr']>;
  serotherapie: FormControl<IFiche['serotherapie']>;
  sAL: FormControl<IFiche['sAL']>;
  doseSAL: FormControl<IFiche['doseSAL']>;
  sourceCellule: FormControl<IFiche['sourceCellule']>;
  sortieAplasie: FormControl<IFiche['sortieAplasie']>;
  gradeaGvH: FormControl<IFiche['gradeaGvH']>;
  cGvH: FormControl<IFiche['cGvH']>;
  mVO: FormControl<IFiche['mVO']>;
  complicationPulmonaire: FormControl<IFiche['complicationPulmonaire']>;
  preciseCompPulm: FormControl<IFiche['preciseCompPulm']>;
  survieJ100: FormControl<IFiche['survieJ100']>;
  sMD: FormControl<IFiche['sMD']>;
  ageDiagSMD: FormControl<IFiche['ageDiagSMD']>;
  critereDiagSMD: FormControl<IFiche['critereDiagSMD']>;
  traitementSMD: FormControl<IFiche['traitementSMD']>;
  lAM: FormControl<IFiche['lAM']>;
  critereDiagLAM: FormControl<IFiche['critereDiagLAM']>;
  traitementLAM: FormControl<IFiche['traitementLAM']>;
  autresCancers: FormControl<IFiche['autresCancers']>;
  dDN: FormControl<IFiche['dDN']>;
  transformationAigue: FormControl<IFiche['transformationAigue']>;
  ageDiagLA: FormControl<IFiche['ageDiagLA']>;
  traitementLA: FormControl<IFiche['traitementLA']>;
  cancerE: FormControl<IFiche['cancerE']>;
  localisation: FormControl<IFiche['localisation']>;
  typeHistologique: FormControl<IFiche['typeHistologique']>;
  traitementCancer: FormControl<IFiche['traitementCancer']>;
  preciseTraitement: FormControl<IFiche['preciseTraitement']>;
  evolutionCyto: FormControl<IFiche['evolutionCyto']>;
  formuleChrom: FormControl<IFiche['formuleChrom']>;
  ageE: FormControl<IFiche['ageE']>;
  statut: FormControl<IFiche['statut']>;
  causeDeces: FormControl<IFiche['causeDeces']>;
  autreCauseD: FormControl<IFiche['autreCauseD']>;
  survieGlobale: FormControl<IFiche['survieGlobale']>;
  code: FormControl<IFiche['code']>;
  dateMAJ: FormControl<IFiche['dateMAJ']>;
  nombreTacheCafe: FormControl<IFiche['nombreTacheCafe']>;
  nombreTacheHypo: FormControl<IFiche['nombreTacheHypo']>;
};

export type FicheFormGroup = FormGroup<FicheFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FicheFormService {
  createFicheFormGroup(fiche: FicheFormGroupInput = { id: null }): FicheFormGroup {
    const ficheRawValue = {
      ...this.getFormDefaults(),
      ...fiche,
    };
    return new FormGroup<FicheFormGroupContent>({
      id: new FormControl(
        { value: ficheRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nDossier: new FormControl(ficheRawValue.nDossier),
      dateDiagnostic: new FormControl(ficheRawValue.dateDiagnostic),
      dateEnregistrement: new FormControl(ficheRawValue.dateEnregistrement),
      medecin: new FormControl(ficheRawValue.medecin),
      hopital: new FormControl(ficheRawValue.hopital),
      service: new FormControl(ficheRawValue.service),
      degConsang: new FormControl(ficheRawValue.degConsang),
      placeEnfant: new FormControl(ficheRawValue.placeEnfant),
      nbVivant: new FormControl(ficheRawValue.nbVivant),
      nbMort: new FormControl(ficheRawValue.nbMort),
      mortNe: new FormControl(ficheRawValue.mortNe),
      avortement: new FormControl(ficheRawValue.avortement),
      cousin: new FormControl(ficheRawValue.cousin),
      membre: new FormControl(ficheRawValue.membre),
      arbregenealogique: new FormControl(ficheRawValue.arbregenealogique),
      arbregenealogiqueContentType: new FormControl(ficheRawValue.arbregenealogiqueContentType),
      arbregenealogiquecancer: new FormControl(ficheRawValue.arbregenealogiquecancer),
      arbregenealogiquecancerContentType: new FormControl(ficheRawValue.arbregenealogiquecancerContentType),
      syndromeAnemique: new FormControl(ficheRawValue.syndromeAnemique),
      syndromeHem: new FormControl(ficheRawValue.syndromeHem),
      syndromeInf: new FormControl(ficheRawValue.syndromeInf),
      decouverteFort: new FormControl(ficheRawValue.decouverteFort),
      enqueteFam: new FormControl(ficheRawValue.enqueteFam),
      typeCancer: new FormControl(ficheRawValue.typeCancer),
      cancer: new FormControl(ficheRawValue.cancer),
      tailleNaiss: new FormControl(ficheRawValue.tailleNaiss),
      poidsNaiss: new FormControl(ficheRawValue.poidsNaiss),
      hypotrophie: new FormControl(ficheRawValue.hypotrophie),
      troubleCroi: new FormControl(ficheRawValue.troubleCroi),
      aAgeChDiag: new FormControl(ficheRawValue.aAgeChDiag),
      mAgeChDiag: new FormControl(ficheRawValue.mAgeChDiag),
      aAgeOssDiag: new FormControl(ficheRawValue.aAgeOssDiag),
      mAgeOssDiag: new FormControl(ficheRawValue.mAgeOssDiag),
      ageRetard: new FormControl(ficheRawValue.ageRetard),
      poids: new FormControl(ficheRawValue.poids),
      poidsDS: new FormControl(ficheRawValue.poidsDS),
      taille: new FormControl(ficheRawValue.taille),
      tailleDS: new FormControl(ficheRawValue.tailleDS),
      atteinteCut: new FormControl(ficheRawValue.atteinteCut),
      tacheCaf: new FormControl(ficheRawValue.tacheCaf),
      ventre: new FormControl(ficheRawValue.ventre),
      membreSup: new FormControl(ficheRawValue.membreSup),
      membreInf: new FormControl(ficheRawValue.membreInf),
      visage: new FormControl(ficheRawValue.visage),
      thorax: new FormControl(ficheRawValue.thorax),
      dOS: new FormControl(ficheRawValue.dOS),
      hyperPig: new FormControl(ficheRawValue.hyperPig),
      hypochromique: new FormControl(ficheRawValue.hypochromique),
      couleurPeau: new FormControl(ficheRawValue.couleurPeau),
      autreAtCut: new FormControl(ficheRawValue.autreAtCut),
      atteinteTete: new FormControl(ficheRawValue.atteinteTete),
      microcephalie: new FormControl(ficheRawValue.microcephalie),
      microphtalmie: new FormControl(ficheRawValue.microphtalmie),
      facieTrig: new FormControl(ficheRawValue.facieTrig),
      traitsFin: new FormControl(ficheRawValue.traitsFin),
      autreAtTete: new FormControl(ficheRawValue.autreAtTete),
      empreintedigitiforme: new FormControl(ficheRawValue.empreintedigitiforme),
      malUro: new FormControl(ficheRawValue.malUro),
      uIV: new FormControl(ficheRawValue.uIV),
      echo: new FormControl(ficheRawValue.echo),
      reinEctop: new FormControl(ficheRawValue.reinEctop),
      siegeEctopie: new FormControl(ficheRawValue.siegeEctopie),
      reinFerChev: new FormControl(ficheRawValue.reinFerChev),
      petitRein: new FormControl(ficheRawValue.petitRein),
      reinUnique: new FormControl(ficheRawValue.reinUnique),
      ectopTest: new FormControl(ficheRawValue.ectopTest),
      vergeInsuf: new FormControl(ficheRawValue.vergeInsuf),
      autreAnomVerge: new FormControl(ficheRawValue.autreAnomVerge),
      retardPubertaire: new FormControl(ficheRawValue.retardPubertaire),
      mTanner: new FormControl(ficheRawValue.mTanner),
      pTanner: new FormControl(ficheRawValue.pTanner),
      tTanner: new FormControl(ficheRawValue.tTanner),
      anomUrin: new FormControl(ficheRawValue.anomUrin),
      typeAnomUrin: new FormControl(ficheRawValue.typeAnomUrin),
      atteinteOss: new FormControl(ficheRawValue.atteinteOss),
      radiosfaites: new FormControl(ficheRawValue.radiosfaites),
      anomPouce: new FormControl(ficheRawValue.anomPouce),
      surnumerarie: new FormControl(ficheRawValue.surnumerarie),
      agenesiePouce: new FormControl(ficheRawValue.agenesiePouce),
      bifide: new FormControl(ficheRawValue.bifide),
      hypoPouce: new FormControl(ficheRawValue.hypoPouce),
      aspectPouce: new FormControl(ficheRawValue.aspectPouce),
      hypoEminence: new FormControl(ficheRawValue.hypoEminence),
      absenceRadial: new FormControl(ficheRawValue.absenceRadial),
      pouceBas: new FormControl(ficheRawValue.pouceBas),
      autreAnomPouce: new FormControl(ficheRawValue.autreAnomPouce),
      anomAutDoigts: new FormControl(ficheRawValue.anomAutDoigts),
      courtstrapus: new FormControl(ficheRawValue.courtstrapus),
      syndactylie: new FormControl(ficheRawValue.syndactylie),
      autreAnomDoigts: new FormControl(ficheRawValue.autreAnomDoigts),
      anomalieos: new FormControl(ficheRawValue.anomalieos),
      agenesieRadius: new FormControl(ficheRawValue.agenesieRadius),
      autreanomalieMembresup: new FormControl(ficheRawValue.autreanomalieMembresup),
      anomOrteil: new FormControl(ficheRawValue.anomOrteil),
      preciseAnomOrt: new FormControl(ficheRawValue.preciseAnomOrt),
      lCH: new FormControl(ficheRawValue.lCH),
      autreanomalieMembreinf: new FormControl(ficheRawValue.autreanomalieMembreinf),
      anomRachis: new FormControl(ficheRawValue.anomRachis),
      preciseAnomRac: new FormControl(ficheRawValue.preciseAnomRac),
      autreAnomOss: new FormControl(ficheRawValue.autreAnomOss),
      anomNeuro: new FormControl(ficheRawValue.anomNeuro),
      retardMent: new FormControl(ficheRawValue.retardMent),
      hypoacousie: new FormControl(ficheRawValue.hypoacousie),
      strabisme: new FormControl(ficheRawValue.strabisme),
      performanceScolaire: new FormControl(ficheRawValue.performanceScolaire),
      autreanomalieneurologique: new FormControl(ficheRawValue.autreanomalieneurologique),
      anomCardVas: new FormControl(ficheRawValue.anomCardVas),
      echoCoeur: new FormControl(ficheRawValue.echoCoeur),
      preciseAnomCardio: new FormControl(ficheRawValue.preciseAnomCardio),
      anomDig: new FormControl(ficheRawValue.anomDig),
      preciseAnomDig: new FormControl(ficheRawValue.preciseAnomDig),
      endocrinopathie: new FormControl(ficheRawValue.endocrinopathie),
      diabete: new FormControl(ficheRawValue.diabete),
      insulinoDep: new FormControl(ficheRawValue.insulinoDep),
      hypothyroidie: new FormControl(ficheRawValue.hypothyroidie),
      ageDecouverte: new FormControl(ficheRawValue.ageDecouverte),
      autreEndocrinopathie: new FormControl(ficheRawValue.autreEndocrinopathie),
      autreAnomCong: new FormControl(ficheRawValue.autreAnomCong),
      dateNumSanguine: new FormControl(ficheRawValue.dateNumSanguine),
      age: new FormControl(ficheRawValue.age),
      hb: new FormControl(ficheRawValue.hb),
      vGM: new FormControl(ficheRawValue.vGM),
      retic: new FormControl(ficheRawValue.retic),
      leuco: new FormControl(ficheRawValue.leuco),
      pNN: new FormControl(ficheRawValue.pNN),
      plq: new FormControl(ficheRawValue.plq),
      hbInf: new FormControl(ficheRawValue.hbInf),
      plqInf: new FormControl(ficheRawValue.plqInf),
      pNNInf: new FormControl(ficheRawValue.pNNInf),
      electrophoreseHb: new FormControl(ficheRawValue.electrophoreseHb),
      hbf: new FormControl(ficheRawValue.hbf),
      myelogramme: new FormControl(ficheRawValue.myelogramme),
      cellularite: new FormControl(ficheRawValue.cellularite),
      erythroblaste: new FormControl(ficheRawValue.erythroblaste),
      morphologieEryth: new FormControl(ficheRawValue.morphologieEryth),
      morphologieGran: new FormControl(ficheRawValue.morphologieGran),
      morphologieMega: new FormControl(ficheRawValue.morphologieMega),
      granuleux: new FormControl(ficheRawValue.granuleux),
      dysmyelopoiese: new FormControl(ficheRawValue.dysmyelopoiese),
      megacaryocytes: new FormControl(ficheRawValue.megacaryocytes),
      blaste: new FormControl(ficheRawValue.blaste),
      excesblastes: new FormControl(ficheRawValue.excesblastes),
      bOM: new FormControl(ficheRawValue.bOM),
      adipocytes: new FormControl(ficheRawValue.adipocytes),
      ubiquitination: new FormControl(ficheRawValue.ubiquitination),
      resUbiquitination: new FormControl(ficheRawValue.resUbiquitination),
      groupComp: new FormControl(ficheRawValue.groupComp),
      mutationFANC: new FormControl(ficheRawValue.mutationFANC),
      congelationCellule: new FormControl(ficheRawValue.congelationCellule),
      labo: new FormControl(ficheRawValue.labo),
      typePrelevement: new FormControl(ficheRawValue.typePrelevement),
      scoreClinique: new FormControl(ficheRawValue.scoreClinique),
      scoreEBMT: new FormControl(ficheRawValue.scoreEBMT),
      score: new FormControl(ficheRawValue.score),
      transfusion: new FormControl(ficheRawValue.transfusion),
      ageTransf: new FormControl(ficheRawValue.ageTransf),
      delaiDiag: new FormControl(ficheRawValue.delaiDiag),
      nbCG: new FormControl(ficheRawValue.nbCG),
      nbCP: new FormControl(ficheRawValue.nbCP),
      chelationFer: new FormControl(ficheRawValue.chelationFer),
      chelateur: new FormControl(ficheRawValue.chelateur),
      nilevar: new FormControl(ficheRawValue.nilevar),
      danatrol: new FormControl(ficheRawValue.danatrol),
      oxynethadone: new FormControl(ficheRawValue.oxynethadone),
      androtordyl: new FormControl(ficheRawValue.androtordyl),
      autreAndrogene: new FormControl(ficheRawValue.autreAndrogene),
      androDebut: new FormControl(ficheRawValue.androDebut),
      androFin: new FormControl(ficheRawValue.androFin),
      observance: new FormControl(ficheRawValue.observance),
      toxicite: new FormControl(ficheRawValue.toxicite),
      autreToxicite: new FormControl(ficheRawValue.autreToxicite),
      enqueteHLA: new FormControl(ficheRawValue.enqueteHLA),
      pourquoiEnqHLA: new FormControl(ficheRawValue.pourquoiEnqHLA),
      nbFratTyp: new FormControl(ficheRawValue.nbFratTyp),
      nbFratNTyp: new FormControl(ficheRawValue.nbFratNTyp),
      donneurComp: new FormControl(ficheRawValue.donneurComp),
      preciseDonneur: new FormControl(ficheRawValue.preciseDonneur),
      donneur: new FormControl(ficheRawValue.donneur),
      greffeFaite: new FormControl(ficheRawValue.greffeFaite),
      delaiRappDiag: new FormControl(ficheRawValue.delaiRappDiag),
      pourquoiNfaite: new FormControl(ficheRawValue.pourquoiNfaite),
      cyclophosphamide: new FormControl(ficheRawValue.cyclophosphamide),
      fludarabine: new FormControl(ficheRawValue.fludarabine),
      busulfan: new FormControl(ficheRawValue.busulfan),
      doseCyclo: new FormControl(ficheRawValue.doseCyclo),
      doseFlu: new FormControl(ficheRawValue.doseFlu),
      doseBus: new FormControl(ficheRawValue.doseBus),
      autreConditionnement: new FormControl(ficheRawValue.autreConditionnement),
      irradiation: new FormControl(ficheRawValue.irradiation),
      doseTotaleIrr: new FormControl(ficheRawValue.doseTotaleIrr),
      serotherapie: new FormControl(ficheRawValue.serotherapie),
      sAL: new FormControl(ficheRawValue.sAL),
      doseSAL: new FormControl(ficheRawValue.doseSAL),
      sourceCellule: new FormControl(ficheRawValue.sourceCellule),
      sortieAplasie: new FormControl(ficheRawValue.sortieAplasie),
      gradeaGvH: new FormControl(ficheRawValue.gradeaGvH),
      cGvH: new FormControl(ficheRawValue.cGvH),
      mVO: new FormControl(ficheRawValue.mVO),
      complicationPulmonaire: new FormControl(ficheRawValue.complicationPulmonaire),
      preciseCompPulm: new FormControl(ficheRawValue.preciseCompPulm),
      survieJ100: new FormControl(ficheRawValue.survieJ100),
      sMD: new FormControl(ficheRawValue.sMD),
      ageDiagSMD: new FormControl(ficheRawValue.ageDiagSMD),
      critereDiagSMD: new FormControl(ficheRawValue.critereDiagSMD),
      traitementSMD: new FormControl(ficheRawValue.traitementSMD),
      lAM: new FormControl(ficheRawValue.lAM),
      critereDiagLAM: new FormControl(ficheRawValue.critereDiagLAM),
      traitementLAM: new FormControl(ficheRawValue.traitementLAM),
      autresCancers: new FormControl(ficheRawValue.autresCancers),
      dDN: new FormControl(ficheRawValue.dDN),
      transformationAigue: new FormControl(ficheRawValue.transformationAigue),
      ageDiagLA: new FormControl(ficheRawValue.ageDiagLA),
      traitementLA: new FormControl(ficheRawValue.traitementLA),
      cancerE: new FormControl(ficheRawValue.cancerE),
      localisation: new FormControl(ficheRawValue.localisation),
      typeHistologique: new FormControl(ficheRawValue.typeHistologique),
      traitementCancer: new FormControl(ficheRawValue.traitementCancer),
      preciseTraitement: new FormControl(ficheRawValue.preciseTraitement),
      evolutionCyto: new FormControl(ficheRawValue.evolutionCyto),
      formuleChrom: new FormControl(ficheRawValue.formuleChrom),
      ageE: new FormControl(ficheRawValue.ageE),
      statut: new FormControl(ficheRawValue.statut),
      causeDeces: new FormControl(ficheRawValue.causeDeces),
      autreCauseD: new FormControl(ficheRawValue.autreCauseD),
      survieGlobale: new FormControl(ficheRawValue.survieGlobale),
      code: new FormControl(ficheRawValue.code),
      dateMAJ: new FormControl(ficheRawValue.dateMAJ),
      nombreTacheCafe: new FormControl(ficheRawValue.nombreTacheCafe),
      nombreTacheHypo: new FormControl(ficheRawValue.nombreTacheHypo),
    });
  }

  getFiche(form: FicheFormGroup): IFiche | NewFiche {
    return form.getRawValue() as IFiche | NewFiche;
  }

  resetForm(form: FicheFormGroup, fiche: FicheFormGroupInput): void {
    const ficheRawValue = { ...this.getFormDefaults(), ...fiche };
    form.reset(
      {
        ...ficheRawValue,
        id: { value: ficheRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FicheFormDefaults {
    return {
      id: null,
    };
  }
}
