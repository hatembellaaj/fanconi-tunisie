import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ICytogenetique } from '../cytogenetique.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../cytogenetique.test-samples';

import { CytogenetiqueService, RestCytogenetique } from './cytogenetique.service';

const requireRestSample: RestCytogenetique = {
  ...sampleWithRequiredData,
  dateSang: sampleWithRequiredData.dateSang?.format(DATE_FORMAT),
  dateMoelle: sampleWithRequiredData.dateMoelle?.format(DATE_FORMAT),
};

describe('Cytogenetique Service', () => {
  let service: CytogenetiqueService;
  let httpMock: HttpTestingController;
  let expectedResult: ICytogenetique | ICytogenetique[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(CytogenetiqueService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Cytogenetique', () => {
      const cytogenetique = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(cytogenetique).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Cytogenetique', () => {
      const cytogenetique = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(cytogenetique).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Cytogenetique', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Cytogenetique', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Cytogenetique', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCytogenetiqueToCollectionIfMissing', () => {
      it('should add a Cytogenetique to an empty array', () => {
        const cytogenetique: ICytogenetique = sampleWithRequiredData;
        expectedResult = service.addCytogenetiqueToCollectionIfMissing([], cytogenetique);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(cytogenetique);
      });

      it('should not add a Cytogenetique to an array that contains it', () => {
        const cytogenetique: ICytogenetique = sampleWithRequiredData;
        const cytogenetiqueCollection: ICytogenetique[] = [
          {
            ...cytogenetique,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCytogenetiqueToCollectionIfMissing(cytogenetiqueCollection, cytogenetique);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Cytogenetique to an array that doesn't contain it", () => {
        const cytogenetique: ICytogenetique = sampleWithRequiredData;
        const cytogenetiqueCollection: ICytogenetique[] = [sampleWithPartialData];
        expectedResult = service.addCytogenetiqueToCollectionIfMissing(cytogenetiqueCollection, cytogenetique);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(cytogenetique);
      });

      it('should add only unique Cytogenetique to an array', () => {
        const cytogenetiqueArray: ICytogenetique[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const cytogenetiqueCollection: ICytogenetique[] = [sampleWithRequiredData];
        expectedResult = service.addCytogenetiqueToCollectionIfMissing(cytogenetiqueCollection, ...cytogenetiqueArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const cytogenetique: ICytogenetique = sampleWithRequiredData;
        const cytogenetique2: ICytogenetique = sampleWithPartialData;
        expectedResult = service.addCytogenetiqueToCollectionIfMissing([], cytogenetique, cytogenetique2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(cytogenetique);
        expect(expectedResult).toContain(cytogenetique2);
      });

      it('should accept null and undefined values', () => {
        const cytogenetique: ICytogenetique = sampleWithRequiredData;
        expectedResult = service.addCytogenetiqueToCollectionIfMissing([], null, cytogenetique, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(cytogenetique);
      });

      it('should return initial array if no Cytogenetique is added', () => {
        const cytogenetiqueCollection: ICytogenetique[] = [sampleWithRequiredData];
        expectedResult = service.addCytogenetiqueToCollectionIfMissing(cytogenetiqueCollection, undefined, null);
        expect(expectedResult).toEqual(cytogenetiqueCollection);
      });
    });

    describe('compareCytogenetique', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCytogenetique(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCytogenetique(entity1, entity2);
        const compareResult2 = service.compareCytogenetique(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCytogenetique(entity1, entity2);
        const compareResult2 = service.compareCytogenetique(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCytogenetique(entity1, entity2);
        const compareResult2 = service.compareCytogenetique(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
