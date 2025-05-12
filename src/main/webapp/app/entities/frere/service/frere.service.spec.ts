import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IFrere } from '../frere.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../frere.test-samples';

import { FrereService } from './frere.service';

const requireRestSample: IFrere = {
  ...sampleWithRequiredData,
};

describe('Frere Service', () => {
  let service: FrereService;
  let httpMock: HttpTestingController;
  let expectedResult: IFrere | IFrere[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(FrereService);
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

    it('should create a Frere', () => {
      const frere = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(frere).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Frere', () => {
      const frere = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(frere).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Frere', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Frere', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Frere', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFrereToCollectionIfMissing', () => {
      it('should add a Frere to an empty array', () => {
        const frere: IFrere = sampleWithRequiredData;
        expectedResult = service.addFrereToCollectionIfMissing([], frere);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(frere);
      });

      it('should not add a Frere to an array that contains it', () => {
        const frere: IFrere = sampleWithRequiredData;
        const frereCollection: IFrere[] = [
          {
            ...frere,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFrereToCollectionIfMissing(frereCollection, frere);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Frere to an array that doesn't contain it", () => {
        const frere: IFrere = sampleWithRequiredData;
        const frereCollection: IFrere[] = [sampleWithPartialData];
        expectedResult = service.addFrereToCollectionIfMissing(frereCollection, frere);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(frere);
      });

      it('should add only unique Frere to an array', () => {
        const frereArray: IFrere[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const frereCollection: IFrere[] = [sampleWithRequiredData];
        expectedResult = service.addFrereToCollectionIfMissing(frereCollection, ...frereArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const frere: IFrere = sampleWithRequiredData;
        const frere2: IFrere = sampleWithPartialData;
        expectedResult = service.addFrereToCollectionIfMissing([], frere, frere2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(frere);
        expect(expectedResult).toContain(frere2);
      });

      it('should accept null and undefined values', () => {
        const frere: IFrere = sampleWithRequiredData;
        expectedResult = service.addFrereToCollectionIfMissing([], null, frere, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(frere);
      });

      it('should return initial array if no Frere is added', () => {
        const frereCollection: IFrere[] = [sampleWithRequiredData];
        expectedResult = service.addFrereToCollectionIfMissing(frereCollection, undefined, null);
        expect(expectedResult).toEqual(frereCollection);
      });
    });

    describe('compareFrere', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFrere(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareFrere(entity1, entity2);
        const compareResult2 = service.compareFrere(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareFrere(entity1, entity2);
        const compareResult2 = service.compareFrere(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareFrere(entity1, entity2);
        const compareResult2 = service.compareFrere(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
