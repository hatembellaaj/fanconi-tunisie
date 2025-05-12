import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IAndrogene } from '../androgene.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../androgene.test-samples';

import { AndrogeneService } from './androgene.service';

const requireRestSample: IAndrogene = {
  ...sampleWithRequiredData,
};

describe('Androgene Service', () => {
  let service: AndrogeneService;
  let httpMock: HttpTestingController;
  let expectedResult: IAndrogene | IAndrogene[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(AndrogeneService);
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

    it('should create a Androgene', () => {
      const androgene = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(androgene).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Androgene', () => {
      const androgene = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(androgene).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Androgene', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Androgene', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Androgene', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAndrogeneToCollectionIfMissing', () => {
      it('should add a Androgene to an empty array', () => {
        const androgene: IAndrogene = sampleWithRequiredData;
        expectedResult = service.addAndrogeneToCollectionIfMissing([], androgene);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(androgene);
      });

      it('should not add a Androgene to an array that contains it', () => {
        const androgene: IAndrogene = sampleWithRequiredData;
        const androgeneCollection: IAndrogene[] = [
          {
            ...androgene,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAndrogeneToCollectionIfMissing(androgeneCollection, androgene);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Androgene to an array that doesn't contain it", () => {
        const androgene: IAndrogene = sampleWithRequiredData;
        const androgeneCollection: IAndrogene[] = [sampleWithPartialData];
        expectedResult = service.addAndrogeneToCollectionIfMissing(androgeneCollection, androgene);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(androgene);
      });

      it('should add only unique Androgene to an array', () => {
        const androgeneArray: IAndrogene[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const androgeneCollection: IAndrogene[] = [sampleWithRequiredData];
        expectedResult = service.addAndrogeneToCollectionIfMissing(androgeneCollection, ...androgeneArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const androgene: IAndrogene = sampleWithRequiredData;
        const androgene2: IAndrogene = sampleWithPartialData;
        expectedResult = service.addAndrogeneToCollectionIfMissing([], androgene, androgene2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(androgene);
        expect(expectedResult).toContain(androgene2);
      });

      it('should accept null and undefined values', () => {
        const androgene: IAndrogene = sampleWithRequiredData;
        expectedResult = service.addAndrogeneToCollectionIfMissing([], null, androgene, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(androgene);
      });

      it('should return initial array if no Androgene is added', () => {
        const androgeneCollection: IAndrogene[] = [sampleWithRequiredData];
        expectedResult = service.addAndrogeneToCollectionIfMissing(androgeneCollection, undefined, null);
        expect(expectedResult).toEqual(androgeneCollection);
      });
    });

    describe('compareAndrogene', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAndrogene(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAndrogene(entity1, entity2);
        const compareResult2 = service.compareAndrogene(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAndrogene(entity1, entity2);
        const compareResult2 = service.compareAndrogene(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAndrogene(entity1, entity2);
        const compareResult2 = service.compareAndrogene(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
