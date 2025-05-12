import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { ICytogeneticien } from '../cytogeneticien.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../cytogeneticien.test-samples';

import { CytogeneticienService } from './cytogeneticien.service';

const requireRestSample: ICytogeneticien = {
  ...sampleWithRequiredData,
};

describe('Cytogeneticien Service', () => {
  let service: CytogeneticienService;
  let httpMock: HttpTestingController;
  let expectedResult: ICytogeneticien | ICytogeneticien[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(CytogeneticienService);
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

    it('should create a Cytogeneticien', () => {
      const cytogeneticien = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(cytogeneticien).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Cytogeneticien', () => {
      const cytogeneticien = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(cytogeneticien).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Cytogeneticien', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Cytogeneticien', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Cytogeneticien', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCytogeneticienToCollectionIfMissing', () => {
      it('should add a Cytogeneticien to an empty array', () => {
        const cytogeneticien: ICytogeneticien = sampleWithRequiredData;
        expectedResult = service.addCytogeneticienToCollectionIfMissing([], cytogeneticien);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(cytogeneticien);
      });

      it('should not add a Cytogeneticien to an array that contains it', () => {
        const cytogeneticien: ICytogeneticien = sampleWithRequiredData;
        const cytogeneticienCollection: ICytogeneticien[] = [
          {
            ...cytogeneticien,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCytogeneticienToCollectionIfMissing(cytogeneticienCollection, cytogeneticien);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Cytogeneticien to an array that doesn't contain it", () => {
        const cytogeneticien: ICytogeneticien = sampleWithRequiredData;
        const cytogeneticienCollection: ICytogeneticien[] = [sampleWithPartialData];
        expectedResult = service.addCytogeneticienToCollectionIfMissing(cytogeneticienCollection, cytogeneticien);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(cytogeneticien);
      });

      it('should add only unique Cytogeneticien to an array', () => {
        const cytogeneticienArray: ICytogeneticien[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const cytogeneticienCollection: ICytogeneticien[] = [sampleWithRequiredData];
        expectedResult = service.addCytogeneticienToCollectionIfMissing(cytogeneticienCollection, ...cytogeneticienArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const cytogeneticien: ICytogeneticien = sampleWithRequiredData;
        const cytogeneticien2: ICytogeneticien = sampleWithPartialData;
        expectedResult = service.addCytogeneticienToCollectionIfMissing([], cytogeneticien, cytogeneticien2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(cytogeneticien);
        expect(expectedResult).toContain(cytogeneticien2);
      });

      it('should accept null and undefined values', () => {
        const cytogeneticien: ICytogeneticien = sampleWithRequiredData;
        expectedResult = service.addCytogeneticienToCollectionIfMissing([], null, cytogeneticien, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(cytogeneticien);
      });

      it('should return initial array if no Cytogeneticien is added', () => {
        const cytogeneticienCollection: ICytogeneticien[] = [sampleWithRequiredData];
        expectedResult = service.addCytogeneticienToCollectionIfMissing(cytogeneticienCollection, undefined, null);
        expect(expectedResult).toEqual(cytogeneticienCollection);
      });
    });

    describe('compareCytogeneticien', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCytogeneticien(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCytogeneticien(entity1, entity2);
        const compareResult2 = service.compareCytogeneticien(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCytogeneticien(entity1, entity2);
        const compareResult2 = service.compareCytogeneticien(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCytogeneticien(entity1, entity2);
        const compareResult2 = service.compareCytogeneticien(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
